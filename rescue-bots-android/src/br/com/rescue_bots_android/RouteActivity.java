package br.com.rescue_bots_android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import br.com.rescue_bots_android.bluetooth.adapter.RouteListAdapter;
import br.com.rescue_bots_android.controller.RouteController;
import br.com.rescue_bots_android.sqlite.entity.Path;

public class RouteActivity  extends Activity  {
	
	RouteController controller = null;
	
	private EditText editTextLat;
	private EditText editTextLong;
	private ListView listViewRoute;
	private RouteListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
			setContentView(R.layout.activity_route);
			
			controller = new RouteController(this);
			
			editTextLat = (EditText)findViewById( R.id.editTextLat );
			editTextLong = (EditText)findViewById( R.id.editTextLong );
			 Bundle extras = getIntent().getExtras();
		     String latitude = extras.getString("latitude");
		     String longitude = extras.getString("longitude");
		     if(latitude!=null){
		    	 editTextLat.setText(latitude);
		     }
		     if(longitude!=null){
		    	 editTextLong.setText(longitude);
		     }
			
			listViewRoute = (ListView)findViewById( R.id.listViewRoute );
			
			List<Path> list = new ArrayList<Path>();
			controller.selectAll(list);
			adapter = new RouteListAdapter(this, list);
					
			listViewRoute.setAdapter(adapter);
	}
	public void rmCoordOnClick(View view){
		controller.removeAll();
		adapter.clearItems();
		adapter.notifyDataSetChanged();
		editTextLat.requestFocus();
	}
	public void addCoordOnClick(View view){
		Log.i("INFO","Add Coord");
		Path bean = new Path();
		bean.setLatitude(editTextLat.getText().toString());
		bean.setLongitude(editTextLong.getText().toString());
		WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = manager.getConnectionInfo();
		String address = info.getMacAddress();
		bean.setRobotId(address);
		bean.setFound("NO");
		controller.inserir(bean);
		editTextLat.setText("");
		editTextLong.setText("");
		
		adapter.add(bean);
		adapter.notifyDataSetChanged();
		
		editTextLat.requestFocus();
	}
}