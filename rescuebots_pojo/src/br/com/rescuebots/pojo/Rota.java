package br.com.rescuebots.pojo;

import java.io.Serializable;

public class Rota implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message = "";
	private String usuario = "";
	private String robotid = "";
	//latitude longitude de origem
	private Double[] origem = new Double[2]; 
	//latitude longitude de destino
	private Double[] destino =  new Double[2]; 
	
	
	public Double[] getOrigem() {
		return origem;
	}
	public void setOrigem(Double[] origem) {
		this.origem = origem;
	}
	public Double[] getDestino() {
		return destino;
	}
	public void setDestino(Double[] destino) {
		this.destino = destino;
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