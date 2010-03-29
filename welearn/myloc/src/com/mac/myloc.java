package com.mac;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;



public class myloc extends Activity implements LocationListener {
	/** Called when the activity is first created. */
	private LocationManager myManager;
	private TextView tv;
	MapView mapView;
	MapController mapController;
	GeoPoint gp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// get a handle to our TextView so we can write to it later
		tv = (TextView) findViewById(R.id.tv1);

		// set up the LocationManager
		myManager = (LocationManager) getSystemService(LOCATION_SERVICE);



	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast info = Toast
		.makeText(this, "Go to http://thesundancekid.net/ for help.",
				Toast.LENGTH_LONG);
		info.show();

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast info = Toast
		.makeText(this, "Go to http://thesundancekid.net/ for help.",
				Toast.LENGTH_LONG);
		info.show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Toast info = Toast
		.makeText(this, "Go to http://thesundancekid.net/ for help.",
				Toast.LENGTH_LONG);
		info.show();
	}

	@Override
	protected void onDestroy() {
		stopListening();
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		stopListening();
		super.onPause();
	}

	@Override
	protected void onResume() {
		startListening();
		super.onResume();
	}

	/**************************************************************************
	 * helper functions for starting/stopping monitoring of GPS changes below
	 **************************************************************************/
	private void startListening() {
		myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);
	}

	private void stopListening() {
		if (myManager != null)
			myManager.removeUpdates(this);
	}

	/**************************************************************************
	 * LocationListener overrides below
	 **************************************************************************/
	@Override
	public void onLocationChanged(Location location) {
		Log
		.i(getClass().getSimpleName(), "accuracy: "
				+ location.getAccuracy());

		// we got new location info. lets display it in the textview
		String s = "";
		s += "Time: " + location.getTime() + "\n";
		s += "\tLatitude:  " + location.getLatitude() + "\n";
		s += "\tLongitude: " + location.getLongitude() + "\n";
		s += "\tAccuracy:  " + location.getAccuracy() + "\n";

		tv.setText(s);
	}



}
