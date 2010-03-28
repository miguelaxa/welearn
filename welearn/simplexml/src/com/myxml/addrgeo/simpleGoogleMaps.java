package com.myxml.addrgeo;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.myxml.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

//lots of imports.. see attached source 
public class simpleGoogleMaps extends MapActivity { 
	
	
	protected boolean isRouteDisplayed() { 
          return false; 
     } 
  
     //lots of variables here 

     @Override 
     public void onCreate(Bundle savedInstanceState) { 
          super.onCreate(savedInstanceState); 
          setContentView(R.layout.main); 

          MapView myMap = (MapView) findViewById(R.id.mapView); // Get map from XML 
          Button btnSearch = (Button) findViewById(R.id.simpleGM_btn_search); // Get button from xml 
          final EditText adress = (EditText) findViewById(R.id.simpleGM_adress); // Get address from XML 

          final Geocoder gc = new Geocoder(this); // create new geocoder instance 

          btnSearch.setOnClickListener(new OnClickListener() { 
               public void onClick(View v) { 
                     
                    ProgressDialog pd = ProgressDialog.show(simpleGoogleMaps.this, "Working..", "Searching your address", true, false); //Show a progress dialog 
                
                     
                    Thread searchAdress = new Thread() { 
                         public void run(){ 
                              String addressInput = adress.getText().toString(); // Get input text 
                              try { 
                                   List<Address> foundAdresses = gc.getFromLocationName(addressInput, 5); // Search addresses 
                                   Thread.sleep(1500); //just to show you that it works 
                              } catch (Exception e) { 
                                   // @todo: Show error message 
                              } 
                              showAdressResults.sendEmptyMessage(0);                            
                         } 
                    }; 
                    searchAdress.start(); 
               } 
          }); 
     } 
      
     private Handler showAdressResults = new Handler() { 
             public void handleMessage(Message msg) { 
             //  Dialog pd;
			//pd.dismiss(); 
                  
                    Bundle foundAdresses;
					//if (foundAdresses.size() == 0) { // if no address found, 
                         // display an error 
                    //     Dialog locationError = new AlertDialog.Builder( 
                     //              simpleGoogleMaps.this).setIcon(0).setTitle( 
                     //              "Error").setPositiveButton(R.string.ok, null) 
                     //              .setMessage("Sorry, your address doesn't exist.") 
                     //              .create(); 
                     //    locationError.show(); 
                          
                    //} else { // else display address on map 
                     //    double lat = 0;
					//	double lon = 0;
						//for (int i = 0; i < foundAdresses.size(); ++i) { 
                              // Save results as Longitude and Latitude 
                              // @todo: if more than one result, then show a 
                              // select-list 
                            //  Address x = foundAdresses.get(i);
                           //   lat = x.getLatitude(); 
                           //   lon = x.getLongitude(); 
                       //  } 
                    //     MapView myMap = null;
					////	navigateToLocation((lat * 1000000), (lon * 1000000),myMap); // display the found address 
                    //} 
                     
             } 
         };    

     /** 
      * Navigates a given MapView to the specified Longitude and Latitude 
      * 
      * @param latitude 
      * @param longitude 
      * @param mv 
      */ 
     public static void navigateToLocation(double latitude, double longitude, MapView mv) { 
          GeoPoint p = new GeoPoint((int) latitude, (int) longitude); // new 
          // GeoPoint 
          mv.displayZoomControls(true); // display Zoom (seems that it doesn't 
          // work yet) 
          MapController mc = mv.getController(); 
          mc.animateTo(p); // move map to the given point 
          int zoomlevel = mv.getMaxZoomLevel(); // detect maximum zoom level 
          mc.setZoom(zoomlevel - 1); // zoom 
          mv.setSatellite(false); // display only "normal" mapview 
     } 
} 