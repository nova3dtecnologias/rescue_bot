package br.com.rescuebots_brain.socket;


import java.net.*;
import java.io.*;
/**
 * Classe cliente socket para testes no servidor
 * @author nova3d-webserver01
 *
 */
public class SocketClient extends Thread {
	private String server;
	private int porta;

	public SocketClient(String server, int porta) {
		this.server = server;
		this.porta = porta;
	}

	public static void main(String[] args) {
		try {
			String server = "192.168.1.108";
			int porta = 8082;
			int numeroDeClientes = 3;
			for (int i = 0; i < numeroDeClientes; i++) {
				new SocketClient(server, porta).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				Socket s = new Socket(server, porta);
				System.out.println("Conectado a " + server + ":" + porta);
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				oo.writeObject("Soh!");
				s.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}