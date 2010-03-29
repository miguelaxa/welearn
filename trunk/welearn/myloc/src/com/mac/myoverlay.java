package com.mac;


import java.util.ArrayList; 

import android.content.Context;
import android.graphics.Canvas; 
import android.graphics.drawable.Drawable; 
import android.widget.Toast; 

import com.google.android.maps.ItemizedOverlay; 
import com.google.android.maps.MapActivity; 

import com.google.android.maps.MapView; 
import com.google.android.maps.OverlayItem; 

public class myoverlay extends ItemizedOverlay<OverlayItem> { 
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
private MapActivity mMapActivity; 

private Drawable mMarker; 
private ArrayList<OverlayItem> locationList; 

public myoverlay(MapActivity mapActivity, Drawable defaultMarker) { 
	super(boundCenterBottom(defaultMarker));
}


@Override 
public void draw(Canvas canvas, MapView mapView, boolean shadow) { 
super.draw(canvas, mapView, shadow); 
boundCenterBottom(mMarker); 
} 

@Override 
protected boolean onTap(int i) { 
Toast.makeText(mMapActivity, locationList.get(i).getSnippet(), Toast.LENGTH_SHORT).show(); 

return(true); 
} 

@Override 
protected OverlayItem createItem(int index) { 
OverlayItem oi = locationList.get(index); 
return oi; 
} 

@Override 
public int size() { 
//Return the number of markers in the collection 
int size = locationList.size(); 
return size; 
} 

public myoverlay(Drawable defaultMarker, Context context) {
	  super(defaultMarker);
	  Context mContext = context;
	}

public void addOverlay(OverlayItem overlay) {
    mOverlays.add(overlay);
    populate();
}
} 

