package br.com.rescue_bots_android.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import br.com.rescue_bots_android.ConfigActivity;
import br.com.rescue_bots_android.sqlite.RouteRepositorio;
import br.com.rescue_bots_android.sqlite.entity.Path;

public class RouteController  extends Controller{

	public RouteController(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public long inserir(Path bean){
		if(bean!=null){
			RouteRepositorio repository = null;
			try {
				repository = new RouteRepositorio(activity);
				return repository.inserir(bean);
			} catch (Exception e) {
				Log.e("ERROR", e.getLocalizedMessage(),e);
			}finally{
				repository.close();
			}	
		}
		return 0;
	}
	public void removeAll() {
		RouteRepositorio repository = null;
			try {
				repository = new RouteRepositorio(activity);
				repository.removeAll();
			} catch (Exception e) {
				Log.e("ERROR", e.getLocalizedMessage(),e);
			}finally{
				repository.close();
			}	
	}
	public void selectAll(List<Path> result) {
		RouteRepositorio repository = null;
		try {
			repository = new RouteRepositorio(activity);
			repository.selectAll(result);
		} catch (Exception e) {
			Log.e("ERROR", e.getLocalizedMessage(),e);
		}finally{
			repository.close();
		}	
	}


	
}
