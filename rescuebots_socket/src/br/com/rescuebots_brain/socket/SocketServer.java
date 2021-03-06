package br.com.rescuebots_brain.socket;


import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import br.com.rescuebots_brain.database.Database;
/**
 * Classe servidor, que inicia o serviço na porta especificada em PORTA
 * Essa classe gerencia os clientes que a solicitarem com menssagens via Socket
 * @author nova3d-webserver01
 *
 */
public class SocketServer implements Runnable {
ServerSocket ss;
Database database = new Database(); 
public static final int PORTA = 8086;

	

    public SocketServer(int porta) throws Exception{ 
    	database.initDatabase();
     
    	ss = new ServerSocket(porta);   

        new Thread(this).start();   

	    System.out.println("Brain listener at port : " + porta);
		  System.out.println("-,-.      __,---.__   ,',`\\");
		  System.out.println("-/ `.`. ,-'         `-/ /   )");
		  System.out.println("(    `,'             _ \\   ;");
		  System.out.println("-\\  _( _           ,'  )/  :");
		  System.out.println("--\\ `-( `-.      ,'    /  /");
		  System.out.println("---\\   \\ __`.___/_,-( /_,'");
		  System.out.println("----`--'`,\\_o,(_)o_,',");
		  System.out.println("--------(    /`-'\\    )");
		  System.out.println("---------`--:\\,m//`--'");
		  System.out.println("-------------`--'");
			 
    }   

 public void run(){   
      try{   
           while(true){   
                new SocketClientInvolve(database,ss.accept()).start();   
               
           }   

      }catch(Exception e){   
           e.printStackTrace();   
           System.exit(1);   
      }   
 }   
 public static void main(String[] args){   
      try{   
           new SocketServer(PORTA); 
           System.out.println("BRAIN STARTED ..: we rule the world ?");
      }catch(Exception e){   
           e.printStackTrace();   
           System.exit(1);   
      }   
 }
}