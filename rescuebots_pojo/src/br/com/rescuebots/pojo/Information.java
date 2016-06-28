package br.com.rescuebots.pojo;

import java.io.Serializable;

public class Information implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message = "";
	private String usuario = "";
	private String robotid = "";
	//latitude longitude de origem
	private double latorigem = 0; 
	private double lonorigem = 0; 
	
	
	
	
	
	public double getLatorigem() {
		return latorigem;
	}
	public void setLatorigem(double latorigem) {
		this.latorigem = latorigem;
	}
	public double getLonorigem() {
		return lonorigem;
	}
	public void setLonorigem(double lonorigem) {
		this.lonorigem = lonorigem;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRobotid() {
		return robotid;
	}
	public void setRobotid(String robotid) {
		this.robotid = robotid;
	}
	
}