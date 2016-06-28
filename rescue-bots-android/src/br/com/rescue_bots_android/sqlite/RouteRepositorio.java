package br.com.rescue_bots_android.sqlite;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import br.com.rescue_bots_android.sqlite.entity.Path;

public class RouteRepositorio  extends Repositorio{
	private static final String NOME_TABELA = "route";

	enum NomesColunas {	
		id,
		latitude,
		longitude,
		found,
		robotid;

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
	public RouteRepositorio(Context ctx) {
		super(ctx);
	}
 
	@Override
	public String createStatement() {
		return	"CREATE TABLE IF NOT EXISTS route("+
				   "id int auto_increment primary key,"+
				   "latitude TEXT,"+
				   "longitude TEXT,"+
				   "found TEXT,"+
				   "robotid TEXT"+
				   ")";
		
		//"CREATE TABLE IF NOT EXISTS data (id int auto_increment primary key, data_type "
		//+ "int default 0, data float not null, time timestamp default current_timestamp);"
	}
	
	public List<Long> inserir(List<Path> items){
		
		   getDb().beginTransaction();
		   try {
		     if(items!=null && items.size()>0){
		    	List<Long> results = new ArrayList<Long>();
				for (Path bean : items) {
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
	public long inserir(Path dto) {
		
		ContentValues cv = new ContentValues(); 
		if(dto.getId()!=null){
			 cv.put(NomesColunas.id.name(), dto.getId());
	    }else{
	      	 Long id = super.getIdRandomico();
	      	 cv.put(NomesColunas.id.name(), id);
	    }

		cv.put(NomesColunas.latitude.name(), dto.getLatitude());
		cv.put(NomesColunas.longitude.name(), dto.getLongitude());
		cv.put(NomesColunas.robotid.name(), dto.getRobotId());
		cv.put(NomesColunas.found.name(), dto.getFound());
		

		return getDb().insert(NOME_TABELA, null, cv);
	}
	public void selectAll(List<Path> result) {
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
				Path bean = this.fill(c);  
				result.add(bean);  
			    c.moveToNext();  
			}
		} catch (Exception e) {
			Log.e("ERROR", e.getLocalizedMessage(),e);
		}finally{
			if(c!=null){c.close();}
		}
	}	
	public Path fill(Cursor c){
		try {
			Path bean = new Path();
			
			bean.setId(c.getLong(c.getColumnIndexOrThrow(NomesColunas.id.name())));
			bean.setLatitude(c.getString(c.getColumnIndexOrThrow(NomesColunas.latitude.name())));
			bean.setLongitude(c.getString(c.getColumnIndexOrThrow(NomesColunas.longitude.name())));
			bean.setRobotId(c.getString(c.getColumnIndexOrThrow(NomesColunas.robotid.name())));
			bean.setFound(c.getString(c.getColumnIndexOrThrow(NomesColunas.found.name())));
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