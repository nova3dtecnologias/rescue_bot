package br.com.rescue_bots_android.socket;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class Client extends AsyncTask<String, Void, Void> {

	String dstAddress;
	int dstPort;
	String message = "";
	TextView textResponse;
	Socket s = null;

	public Client(String addr, int port,TextView textResponse) {
		dstAddress = addr;
		dstPort = port;
		this.textResponse=textResponse;
	}
	public Client(String addr, int port,String message,TextView textResponse) {
		dstAddress = addr;
		dstPort = port;
		this.message = message;
		this.textResponse=textResponse;
	}
	@Override
	protected void onPreExecute() {
		textResponse.append("iniciando envio");
		super.onPreExecute();
	}
	@Override
	protected Void doInBackground(String... arg0) {
		String param = arg0[0];
/*		Socket socket = null;

		try {
			socket = new Socket(dstAddress, dstPort);

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
					1024);
			byte[] buffer = new byte[1024];

			int bytesRead;
			InputStream inputStream = socket.getInputStream();

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
				response += byteArrayOutputStream.toString("UTF-8");
			}

		} catch (UnknownHostException e) {
			Log.e("ERROR", e.getLocalizedMessage(),e);
			response = "UnknownHostException: " + e.toString();
		} catch (IOException e) {
			Log.e("ERROR", e.getLocalizedMessage(),e);
			response = "IOException: " + e.toString();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					Log.e("ERROR", e.getLocalizedMessage(),e);
				}
			}
		}
		return null;*/
		
		try {
			//if(param.equalsIgnoreCase("CONNECT")){
				Log.i("Connect", message);
				s = new Socket(dstAddress, dstPort);
			//}else if(param.equalsIgnoreCase("SEND")){
				Log.i("Sending", message);
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				oo.writeObject(message);
			//}else if(param.equalsIgnoreCase("CLOSE")){
				Log.i("Close", message);
				s.close();
			//}

		
			
				
				
			
		
	} catch (Exception e) {
		Log.e("ERROR",e.getMessage());
	}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		textResponse.append(message);
		super.onPostExecute(result);
	}

}
