package br.com.rescue_bots_android.gps;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
public abstract class LocationTracker  extends Service implements LocationListener {
	
	private final Context mContext;
 
    //private List<String> providers = new ArrayList<String>() {{
        //add(LocationManager.GPS_PROVIDER);
    //    add(LocationManager.NETWORK_PROVIDER);
   // }};
	public String provider = LocationManager.NETWORK_PROVIDER;
    private boolean isLocationEnabled = false;
    
    // flag for lotation status
    boolean canGetLocation = false;
 
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude 
 
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 5; // 10 meters
 
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 2000; 
    //private static final long MIN_TIME_BW_UPDATES = 3000; // 3 seconds
    
    
    // Declaring a Location Manager
    protected LocationManager locationManager;
 
    public LocationTracker(Context context){
    	this.mContext = context;
        locationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);

        getLocation();
        
    }
    
    public LocationTracker(Context context, String provider) {
    	
        this.mContext = context;
        locationManager = (LocationManager) mContext
                .getSystemService(LOCATION_SERVICE);


        this.provider = provider;
        
        getLocation();
    }
    

    
    
	//public List<String> getProvider(){
	//	return providers;
	//}
    public Location getLocation() {
        try {
           // for (String provider : providers) {
            	   // getting Location status
                isLocationEnabled = locationManager
                        .isProviderEnabled(provider);
     
                if (!isLocationEnabled) {
                    // no network provider is enabled
                	this.canGetLocation = false;
                	Log.e("ERROR","no network provider is enabled");
                } else {
                    this.canGetLocation = true;
                    Location location = getLocationAux(provider);
                    if(location!=null){
                    	return location;
                    }
                }
			//}
         
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return null;
    }
    Location getLocationAux(String provider){
        //if (location == null) {
            locationManager.requestLocationUpdates(
                    provider,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            
            if (locationManager != null) {
                location = locationManager
                        .getLastKnownLocation(provider);
                if (location != null) {
                    //latitude = location.getLatitude();
                    //longitude = location.getLongitude();
                    Log.i("INFO","provider " + provider + " location " + location.toString());
                    return location;
                }
            }
        //}
			return location;
    }
     
    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(LocationTracker.this);
        }       
    }
     
    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }
         
        // return latitude
        return latitude;
    }
    
    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
         
        // return longitude
        return longitude;
    }
     
    /**
     * Function to check GPS/wifi enable
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }
     
    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     * */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
      
        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");
  
        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
  
        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
  
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
  
        // Showing Alert Message
        alertDialog.show();
    }
 
  

 
}
