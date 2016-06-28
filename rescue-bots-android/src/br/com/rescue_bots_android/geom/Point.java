package br.com.rescue_bots_android.geom;

public class Point {
	Double latitude, longitude;
	public Point(Double latitude, Double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getX(){
		return latitude;
	}
	public double getY(){
		return longitude;
	}
}
