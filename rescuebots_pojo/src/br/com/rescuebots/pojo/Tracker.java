package br.com.rescuebots.pojo;

import java.io.Serializable;

public class Tracker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String accuracy;
	private String altitude;
	private String earing;
	private String latitude;
	private String longitude;
	private String provider;
	private String speed;
	private String time;
	
	private String angle;
	private String direction;
	private String distance;
	private String arduinoChar;
	private String vulforiaObject;
	
	
	
	
	public String getAngle() {
		return angle;
	}
	public void setAngle(String angle) {
		this.angle = angle;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getArduinoChar() {
		return arduinoChar;
	}
	public void setArduinoChar(String arduinoChar) {
		this.arduinoChar = arduinoChar;
	}
	public String getVulforiaObject() {
		return vulforiaObject;
	}
	public void setVulforiaObject(String vulforiaObject) {
		this.vulforiaObject = vulforiaObject;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getEaring() {
		return earing;
	}
	public void setEaring(String earing) {
		this.earing = earing;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	
}
