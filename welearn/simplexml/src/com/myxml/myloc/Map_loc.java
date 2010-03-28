package com.myxml.myloc;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.myxml.R;


public class Map_loc extends MapActivity  { 
	
	LinearLayout linearLayout;
	MapView mapView;
	ZoomControls mZoom;
	
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.mymap); // bind the layout to the activity

		linearLayout = (LinearLayout) findViewById(R.id.zoom);
		mapView = (MapView) findViewById(R.id.mapView);
		mZoom = (ZoomControls) mapView.getZoomControls();
		linearLayout.addView(mZoom);
		
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.icon);
		itemizedOverlay = new HelloItemizedOverlay(drawable);
		
		GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
	}
	
	List<Overlay> mapOverlays;
	Drawable drawable;
	HelloItemizedOverlay itemizedOverlay;
	
}


