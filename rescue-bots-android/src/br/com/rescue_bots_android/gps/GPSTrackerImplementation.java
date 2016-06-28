package br.com.rescue_bots_android.gps;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;

public class GPSTrackerImplementation extends LocationTracker{

	GPSTrackerInterface gps;
	
	public GPSTrackerImplementation(GPSTrackerInterface context) {
		super((Context)context);
		gps = context;
		// TODO Auto-generated constructor stub
	}
	public void setProvider(String providerg){
		super.provider = providerg;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		StringBuilder out = new StringBuilder();
		out.append("----------------------------------- \n");
		out.append("precisão..: " + location.getAccuracy() + "\n");
		out.append("altitude..: " + location.getAltitude() + "\n");
		out.append("condução..: " + location.getBearing() + "\n");
		out.append("latitude..: " +location.getLatitude() + "\n");
		out.append("longitude.: " +location.getLongitude() + "\n");
		out.append("provedor..: " + location.getProvider() + "\n");
		out.append("velocidade: " + location.getSpeed() + "\n");
		out.append("tempo.....: " + location.getTime() + "\n");
	
		if(gps!=null){
		 gps.notifyLocation(location);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

	
	
}
