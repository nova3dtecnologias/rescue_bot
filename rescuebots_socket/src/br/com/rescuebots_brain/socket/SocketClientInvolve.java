package br.com.rescuebots_brain.socket;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.rescuebots.pojo.Tracker;
import br.com.rescuebots_brain.database.Database;
/**
 * Classe para comunicação do server com o client socket
 * Encarregada de instancias a thread que estabelece a comunicação
 * @author nova3d-webserver01
 *
 */
public class SocketClientInvolve extends Thread {
	private Socket client;
	private Database database;

	public SocketClientInvolve(Database database,Socket client) {
		this.client = client;
		this.database = database;
	}
	/**
	 * Aqui ele converte a menssagem em ojeto 
	 * para ser persistido como uma entidade do banco
	 * @param message "param1;param2;param3..."
	 * @return
	 */
    private Tracker fill(String message){
    	if(message!=null){
    		try {
    		  String[] params = message.split(";");
       		  Tracker t = new Tracker();
       		  t.setId(Long.parseLong(params[0]));
       	      t.setAccuracy(params[1]);
       	      t.setAltitude(params[2]); 
       	      t.setEaring(params[3]) ;
       	      t.setLatitude(params[4]); 
       	      t.setLongitude(params[5]);
       	      t.setProvider(params[6]);
       	      t.setSpeed(params[7]) ;
       	      t.setTime(params[8]) ;
       	      t.setFound(params[9]) ;
       	      t.setRoboType(params[10]);
       	      t.setRoboId(params[11]) ;
       	      
       	      
       	      t.setAngle(params[12]);
       	      t.setDirection(params[13]);
       	      t.setDistance(params[14]);
       	      t.setDiference(params[15]);
       	      t.setIndex(params[16]);
       	      t.setLastmessage(params[17]);
       	      t.setFoundsucess(params[18]);
       	          
       	      return t;
			} catch (Exception e) {
				System.err.println("Invalid Message : " + message);
			}
    		 
    	}else{
    		System.err.println("Message is null" );
    	}
    	return null;
    }
	public void run() {
		try {
			// aqui vai a sua comunicacao com o cliente
			ObjectInputStream oi = new ObjectInputStream(client.getInputStream());
			String message = String.valueOf(oi.readObject());
			System.out.println("<Received message : " + message);
			//Aqui ele persiste a coordenada no banco de dados 
			Tracker t = fill(message);
			
			database.insert(t);
			if(t.getFound()!=null && t.getFound().equalsIgnoreCase("YES")){
				String origin = database.getFirstCoordinate(t.getRoboId());
				if(origin!=null){
					ObjectOutputStream oo = new ObjectOutputStream(client.getOutputStream());
					oo.writeObject("0;0;0;0;"+origin+";gps;0.0;1466714754608;YES;"+t.getRoboId());
				}
			}
			client.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}