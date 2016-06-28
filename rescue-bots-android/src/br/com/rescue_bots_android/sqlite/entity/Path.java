package br.com.rescue_bots_android.sqlite.entity;

public class Path {
	private Long id;
	private String latitude;
	private String longitude;
	private String found;
	private String servertime;
	private String robotId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getFound() {
		return found;
	}
	public void setFound(String found) {
		this.found = found;
	}
	public String getServertime() {
		return servertime;
	}
	public void setServertime(String servertime) {
		this.servertime = servertime;
	}
	public String getRobotId() {
		return robotId;
	}
	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}
	public  Double[] toCoordinate(){
		return new Double[]{Double.parseDouble(getLatitude()),Double.parseDouble(getLongitude())};
	}
	
}
