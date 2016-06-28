package br.com.rescue_bots_android.sqlite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/** 
 * @class Repositorio
 * Classe que implementa a responsssabilidade de conexão e 
 * Execução de scripts que preparam o banco, todos os artefatos
 * Que referenciarem essa classe deveriao sobrescrever seu contrutor
 * Passando como parâmetros os artefatos de banco de dados que seram 
 * Criados na instancia SQLITE da aplicação
 * @see database.DbConfig - Enum onde estaram todos os scripts
 * 							e constantes referentes ao banco de dados
*/
public abstract class Repositorio  {
	
	private SQLiteDatabase db;
	private SQLiteHelper dbHelper = null;
	protected Context context;
	
	public static final String DATABASE_NAME = "recuebots_db";
	public static final int DATABASE_VERSION = 1; 

	public Repositorio(Context ctx){
	    this.context = ctx;
	  
		
		if(db!=null){
			db.close();
		}
		dbHelper= new SQLiteHelper(ctx,  
	    		DATABASE_NAME, 
	       		DATABASE_VERSION,   
	       		createStatement(), 
	       		null);  
	    db = dbHelper.getWritableDatabase();

	}
	/*
	public Repositorio(Context ctx){
	    this.context = ctx;
	    // data/user/0/br.com.rescue_bots_android/databases/recuebots_db
	   
		final String DB_DESTINATION = DB_PATH +DATABASE_NAME;
		try {
			// Checa se o banco de dados existe antes de copia-lo
			boolean initialiseDatabase = (new File(DB_DESTINATION)).exists();
			if (initialiseDatabase == false) {
				
			    // Abre o arquivo .db no seu diretório assets
			    InputStream is = ctx.getAssets().open(DATABASE_NAME);
			    
			    File dirDb = new File(DB_PATH);
			    if (!dirDb.exists()){
			    	dirDb.mkdirs();
			    }
			    // Copia o banco para o destino (dentro do dispositivo)
			    OutputStream os = new FileOutputStream(DB_DESTINATION);
			    byte[] buffer = new byte[1024];
			    int length;
			    while ((length = is.read(buffer)) > 0){
			        os.write(buffer, 0, length);
			    }
			    os.flush();
			    os.close();
			    is.close();
			}
		} catch (Exception e) {
			Log.e("ERROR",e.getLocalizedMessage(),e);
		}
		if(db!=null){
			db.close();
		}
		dbHelper= new SQLiteHelper(ctx,  
	    		DATABASE_NAME, 
	       		DATABASE_VERSION,   
	            null, 
	            null);  
	    db = dbHelper.getWritableDatabase();

	}*/
	public abstract String createStatement();
	
	/**
	 * fechar a conexão
	 */
	public void close(){
        db.close();
    }
	/**
	 * encapsulamento da instancia do banco
	 * @return SQLiteDatabase
	 */
	public SQLiteDatabase getDb(){
		return db;
	}
	
	public Cursor query(String table, 
						String[] columns, 
						String selection, 
						String[] selectionArgs, 
						String groupBy, 
						String having, 
						String orderBy){
		if(getDb()!=null){
			Cursor c = getDb().query(table, 
									columns, 
									selection, 
									selectionArgs, 
									groupBy, 
									having, 
									orderBy);
			Log.i("INFO", c.toString() ); 
			return c; 
		}else{
			return null;
		}
	}
	
	
	/**
	 * metodo que gera um ID com o algorítmo compativel com o banco
	 * @return Long - chave única
	 * @throws NoSuchAlgorithmException 
	 */
	public static Long getIdRandomico() {
		long random;
		try {
			random = SecureRandom.getInstance("SHA1PRNG").nextInt(99999999);
			return random;
		} catch (NoSuchAlgorithmException e) {
			//Log.e(context,e.getLocalizedMessage(),e);
		}
		return null;
	}
}
