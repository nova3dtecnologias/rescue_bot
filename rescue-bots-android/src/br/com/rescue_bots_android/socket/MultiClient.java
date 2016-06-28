package br.com.rescue_bots_android.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class MultiClient  implements Runnable {
	private Socket server;
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;
	private String messageFull = "";
	
	private static Vector<ObjectOutputStream> clientesConnectados;
	
	
	public MultiClient(Socket s){
		clientesConnectados = new Vector<ObjectOutputStream>();
		this.server = s;
		
	}
	
	private void getStreams() throws IOException{
		output = new ObjectOutputStream (server.getOutputStream());
		output.flush();
		
		input = new ObjectInputStream( server.getInputStream() );
	}
	
	
	private void processConnection() throws IOException{
		
		do{ 
			try{
				clientesConnectados.add(output);
				String info = (String) input.readObject(); // lê uma nova mensagem mudei para pegar objeto
				System.out.println(server.getInetAddress().getHostName() + " >> " + messageFull);
				sendAll(info);
				clientesConnectados.remove(output);
			}catch(ClassNotFoundException cl){
				cl.printStackTrace();
			}
		}while(!messageFull.contains("FIM"));
	}
	
	
	private void sendAll(String message){
		
		Enumeration<ObjectOutputStream> elem = clientesConnectados.elements();
		while (elem.hasMoreElements()) {
			ObjectOutputStream object = (ObjectOutputStream) elem.nextElement();
			try{
				object.writeObject(message);
				object.flush();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		
	}
	
	
	private void sendAll(String message, String usuario){
		
		Enumeration<ObjectOutputStream> elem = clientesConnectados.elements();
		while (elem.hasMoreElements()) {
			ObjectOutputStream object = (ObjectOutputStream) elem.nextElement();
			try{
				object.writeObject(message);
				object.flush();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		
	}
	
	private void disconnectClient() {
		try{
			if(output != null)output.close();
			if(input != null)input.close();
			if(server != null)server.close();
			System.out.println("Disconecta cliente !!!");
		}catch(IOException io){
			io.printStackTrace();
		}
	}
	
	public void run() {
		try{
			getStreams();
			processConnection();
		}catch(IOException io){
			io.printStackTrace();
		}finally{
			disconnectClient();
		}
	}

}
