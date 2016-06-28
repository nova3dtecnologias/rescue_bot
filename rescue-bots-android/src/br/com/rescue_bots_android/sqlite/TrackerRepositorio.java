package br.com.rescue_bots_android.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import br.com.rescuebots.pojo.Tracker;


public class TrackerRepositorio extends Repositorio{
	private static final String NOME_TABELA = "tracker";
	 /**
    precisão..: Accuracy\n");
		altitude..: Altitude\n");
		condução..: Bearing\n");
		latitude..: Latitude\n");
		longitude.: Longitude\n");
		provedor..: Provider\n");
		velocidade: Speed\n");
		tempo.....: Time\n");
   */
	enum NomesColunas {	
		id,
		accuracy,
		altitude,
		bearing,
		latitude,
		longitude,
		provider,
		speed,
		time;

		public String toString() {
			return this.name();
		}
		public static String[] colunas(){
			String[] colunas = new String[values().length];
			for (int i=0; i<values().length;i++) {
				NomesColunas element = values()[i];
				colunas[i] = element.name();
			}
			return colunas;
		}
	}
	public TrackerRepositorio(Context ctx) {
		super(ctx);
	}
  
	@Override
	public String createStatement() {
		return	"CREATE TABLE IF NOT EXISTS tracker("+
				   "id int auto_increment primary key,"+
				   "accuracy TEXT,"+
				   "altitude TEXT,"+
				   "bearing TEXT,"+
				   "latitude TEXT,"+
				   "longitude TEXT,"+
				   "provider TEXT,"+
				   "speed TEXT,"+
				   "time TEXT"+
				   ")";
		
		//"CREATE TABLE IF NOT EXISTS data (id int auto_increment primary key, data_type "
		//+ "int default 0, data float not null, time timestamp default current_timestamp);"
	}
	
	public List<Long> inserir(List<Tracker> items){
		
		   getDb().beginTransaction();
		   try {
		     if(items!=null && items.size()>0){
		    	List<Long> results = new ArrayList<Long>();
				for (Tracker bean : items) {
					results.add( inserir(bean));
				}
				getDb().setTransactionSuccessful();	
				return results;
		     }
		   } finally {
			 getDb().endTransaction();
		   }
		return null;
	}
	public long inserir(Tracker dto) {
		
		ContentValues cv = new ContentValues(); 
		if(dto.getId()!=null){
			 cv.put(NomesColunas.id.name(), dto.getId());
	    }else{
	      	 Long id = super.getIdRandomico();
	      	 cv.put(NomesColunas.id.name(), id);
	    }
		cv.put(NomesColunas.accuracy.name(), dto.getAltitude());
		cv.put(NomesColunas.altitude.name(), dto.getAltitude());
		cv.put(NomesColunas.bearing.name(), dto.getEaring());
		cv.put(NomesColunas.latitude.name(), dto.getLatitude());
		cv.put(NomesColunas.provider.name(), dto.getProvider());
		cv.put(NomesColunas.speed.name(), dto.getSpeed());
		cv.put(NomesColunas.time.name(), dto.getTime());
		

		return getDb().insert(NOME_TABELA, null, cv);
	}
	public void selectAll(List<Tracker> result) {
		Cursor c = null;
		try {
			c = getDb().query(
					NOME_TABELA, 
			    	NomesColunas.colunas(),   
			    	null, 
			    	null, 
			    	null, 
			    	null, 
			    	NomesColunas.colunas()[1]);  
			c.moveToFirst();  
			while(!c.isAfterLast()){  
				Tracker bean = this.fill(c);  
				result.add(bean);  
			    c.moveToNext();  
			}
		} catch (Exception e) {
			Log.e("ERROR", e.getLocalizedMessage(),e);
		}finally{
			if(c!=null){c.close();}
		}
	}	
	public Tracker fill(Cursor c){
		try {
			Tracker bean = new Tracker();
			
			bean.setId(c.getLong(c.getColumnIndexOrThrow(NomesColunas.id.name())));
			bean.setAccuracy(c.getString(c.getColumnIndexOrThrow(NomesColunas.accuracy.name())));
			bean.setAltitude(c.getString(c.getColumnIndexOrThrow(NomesColunas.altitude.name())));
			bean.setEaring(c.getString(c.getColumnIndexOrThrow(NomesColunas.bearing.name())));
			bean.setLatitude(c.getString(c.getColumnIndexOrThrow(NomesColunas.latitude.name())));
			bean.setLongitude(c.getString(c.getColumnIndexOrThrow(NomesColunas.longitude.name())));
			bean.setProvider(c.getString(c.getColumnIndexOrThrow(NomesColunas.provider.name())));
			bean.setSpeed(c.getString(c.getColumnIndexOrThrow(NomesColunas.speed.name())));
			bean.setTime(c.getString(c.getColumnIndexOrThrow(NomesColunas.time.name())));
			return bean;
		} catch (Exception e) {
			Log.e("ERROR", e.getLocalizedMessage(),e);
		}
		return null;
	}
	public long removeAll(){
		return getDb().delete(
				NOME_TABELA, 
				null, 
				null);
	}
}
