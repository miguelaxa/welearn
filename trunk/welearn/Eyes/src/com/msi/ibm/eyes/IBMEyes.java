package com.msi.ibm.eyes;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.illposed.osc.*;

public class IBMEyes extends Activity implements OnClickListener {
	private Button playButton;
	private TextView accText;
	private SensorManager myManager;
	private List<Sensor> sensors;
	private Sensor accSensor;
	private float oldX, oldY, oldZ = 0f;
	private OSCPortOut sender;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		playButton = (Button) this.findViewById(R.id.play);
		playButton.setOnClickListener(this);

		String destination = "192.168.1.66";
		int destPort = 8008;

		try {
			sender = new OSCPortOut(InetAddress.getByName(destination),
					destPort);
		} catch (Exception e) {
			Log.i("osc", e.toString());
			// throw new ConnectException(e.toString());
		}

		accText = (TextView) findViewById(R.id.xbox);

		// Set Sensor + Manager
		myManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensors = myManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if (sensors.size() > 0) {
			accSensor = sensors.get(0);
		}
	}

	private void updateTV(float x, float y, float z) {
		float thisX = x - oldX * 10;
		float thisY = y - oldY * 10;
		float thisZ = z - oldZ * 10;
		
		OSCMessage msg = new OSCMessage("/play");

		accText.setText("x: " + Math.round(thisX) + ";\n y:"
				+ Math.round(thisY) + ";\n z: " + Math.round(thisZ));

		oldX = x;
		oldY = y;
		oldZ = z;

		// OSCMessage msg = new OSCMessage("/moves");
		msg.addArgument(Math.round(thisX));
		msg.addArgument(Math.round(thisY));
		msg.addArgument(Math.round(thisZ));

		if (msg != null) {
			try {
				// sender.sendPacket(msg);
				sender.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private final SensorEventListener mySensorListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent event) {
			updateTV(event.values[0], event.values[1], event.values[2]);
			
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		myManager.registerListener(mySensorListener, accSensor,
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		myManager.unregisterListener(mySensorListener);
		super.onStop();
	}

	public void onClick(View v) {
		OSCMessage msg = null;

		if (v == playButton) {
			msg = new OSCMessage("/play");

			// } else if (v == stopButton) {
			// msg = new OSCMessage("/stop");
		}

		if (msg != null) {
			try {
				// sender.sendPacket(msg);
				sender.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}