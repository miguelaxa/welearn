package com.myxml;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.myxml.myxml.RssActivity;
import com.myxml.myloc.*;
import com.myxml.mywebkit.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class simplexml extends Activity {
    
	double longitude, latitude;
	String provider;
	
	 private Button example_rss_parser;
	 private Button example_webview;
	 private Button example_location;
	 private Button trackbutton;
	 private TextView disloc;  
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LocationManager location =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location loc = location.getLastKnownLocation(location.GPS_PROVIDER);
        updateWithNewLocation(loc);
        
   
        example_rss_parser = (Button) findViewById(R.id.Btnxml);
	    example_rss_parser.setOnClickListener(new OnClickListener() {

	    	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i_Alert = new Intent(simplexml.this, RssActivity.class);
				startActivity(i_Alert);
			}
	    	
	    });
	    
        example_webview = (Button) findViewById(R.id.Btnwebkit);
        example_webview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i_Alert = new Intent(simplexml.this, ExampleWebkit.class);
				startActivity(i_Alert);
			}
        	
        });
        
        example_location = (Button) findViewById(R.id.Btnloc);
        example_location.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i_Alert = new Intent(simplexml.this, Map_loc.class);
				startActivity(i_Alert);
			}
        	
        });
        
        trackbutton = (Button) findViewById(R.id.track);
        trackbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String textstr = "Your location is " + 
					      longitude + " and " + latitude + " provided by: " + 
					      provider + "";
				disloc = (TextView) findViewById(R.id.display);
				disloc.setText(textstr);
				  
			}
        	
        });
        
        
    }  
    private void updateWithNewLocation(Location l) {
    	  longitude = l.getLongitude();
    	  latitude = l.getLatitude();
    	  provider = l.getProvider();     
    	  }
    
    private final LocationListener locationListener = new LocationListener() {
    	   public void onLocationChanged(Location location) {
    	     updateWithNewLocation(location);
    	   }

    	 public void onProviderDisabled(String provider){}
    	 public void onProviderEnabled(String provider) {}
    	 public void onStatusChanged(String provider, int status, Bundle extras) {}
    	 };
}