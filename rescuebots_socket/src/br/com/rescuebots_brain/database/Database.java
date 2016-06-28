package br.com.rescuebots_brain.database;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rescuebots.pojo.Tracker;

/**
 * Classe relacionada com os requisitos de banco de dados postgresql
 * @author nova3d-webserver01
 *
 */
public class Database {

	Connection connection = null;
	

	public void initDatabase(){
		System.out.println("--------> PostgreSQL Connection");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.177:5432/rescuebots_db", 
					"postgres",
					"postgres");

		} catch (SQLException e) {
			System.err.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("Database initialized!");
		} else {
			System.err.println("Database initialize error!");
		}
	   System.out.println("Litening");
	}

		
		
		
	
	
	
	
	public void insert (Tracker t) {
		
		try {
			 Statement stmt = connection.createStatement();
			  String sql = "INSERT INTO tracker(" + 
		    		  "accuracy, altitude, bearing, latitude, longitude, provider, " +
		    		  "speed, \"time\", found,robot_id," +
		    		  "angle, direction, distance,diference,index,lastmessage,foundsucess, geom)" + 
		              "VALUES ("+
		              "'" + t.getAccuracy()+ "',"+
		              "'" + t.getAltitude() + "',"+
		              "'" + t.getEaring() + "',"+
		              "'" + t.getLatitude() + "',"+
		              "'" + t.getLongitude() + "',"+
		              "'" + t.getProvider() + "',"+
		              "'" + t.getSpeed() + "',"+
		              "'" + t.getTime() + "',"+
		              "'" + t.getFound() + "',"+
		              "'" + t.getRoboId() + "',"+ 
		              
		              "'" + t.getAngle() + "',"+
		              "'" + t.getDirection() + "',"+ 
		              "'" + t.getDistance() + "',"+ 
		              "'" + t.getDiference() + "',"+ 
		              "'" + t.getIndex() + "',"+ 
		              "'" + t.getLastmessage() + "',"+ 
		              "'" + t.getFoundsucess() + "',"+ 
		              
		              "ST_SetSRID(ST_MakePoint(" +
		              "cast("+t.getLongitude()+" as double precision), " +
		              "cast("+t.getLatitude()+" as double precision)), 4326))";
		      stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      
	    
	      
		
		
	}
	
public void update (String roboid) {
		
	      
		try {
			Statement stmt = connection.createStatement();
			 String sql = "UPDATE public.tracker " +
				      "SET found='DONE' "+
				      "  		  WHERE robot_id='"+roboid+"' and found='YES'";
				      stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
	      
	}

	public String getLastCoordinate(String robotid) {
		  try {
	      Statement stmt = connection.createStatement();
	      String sql = "SELECT latitude, longitude from tracker  where robot_id='"+robotid+"'  order by servertime desc limit 1 ";
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next()){
			  return "["+rs.getString(1) +","+ rs.getString(2)+"]";
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		}
	    return null;
	}
	public String getFirstCoordinate(String robotid) {
		  try {
	      Statement stmt = connection.createStatement();
	      String sql = "SELECT latitude, longitude from tracker where robot_id='"+robotid+"' order by servertime limit 1 ";
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next()){
			  return ""+rs.getString(1) +";"+ rs.getString(2)+"";
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		}
	    return null;
	}
	public String getSaviourFoundCoordinate(){
		  try {
	      Statement stmt = connection.createStatement();
	      String sql = "SELECT latitude, longitude from tracker where found='YES' " +
	      		"order by servertime limit 1 ";
		  ResultSet rs = stmt.executeQuery(sql);
		  while(rs.next()){
			  return ""+rs.getString(1) +";"+ rs.getString(2)+"";
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		}
	    return null;
	}
	public String getRobots(){
		 StringBuilder result = new StringBuilder();
		  try {
	      Statement stmt = connection.createStatement();
	      String sql = "SELECT id from robot";
		  ResultSet rs = stmt.executeQuery(sql);
		 
		  result.append("[");
		  while(rs.next()){
			  result.append("'"+rs.getString(1)+"'");
			  if(!rs.isLast()){
				  result.append(",");
			  }
		  }
		  result.append("]");
		} catch (SQLException e) {
		  e.printStackTrace();
		}
	    return result.toString();
	}
	public static void main(String[] args) {
		Database database = new Database();
		database.initDatabase();
	}
}

