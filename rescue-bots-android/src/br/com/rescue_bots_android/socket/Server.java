package br.com.rescue_bots_android.socket;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;
import br.com.rescue_bots_android.MainActivity;

public class Server {
	MainActivity activity;
	ServerSocket serverSocket;
	String message = "";
	static final int socketServerPORT = 8080;

	public Server(MainActivity activity) {
		this.activity = activity;
		Thread socketServerThread = new Thread(new SocketServerThread());
		socketServerThread.start();
	}

	public int getPort() {
		return socketServerPORT;
	}

	public void onDestroy() {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				Log.e("ERROR", e.getLocalizedMessage(),e);
			}
		}
	}

	private class SocketServerThread extends Thread {

		int count = 0;

		@Override
		public void run() {
			try {
				serverSocket = new ServerSocket(socketServerPORT);

				while (true) {
					Socket socket = serverSocket.accept();
					count++;
					message += "#" + count + " from "
							+ socket.getInetAddress() + ":"
							+ socket.getPort() + "\n";

					activity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							activity.appendLog(message);
						}
					});

					SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(
							socket, count);
					socketServerReplyThread.run();

				}
			} catch (IOException e) {
				Log.e(this.getName(),e.getLocalizedMessage(),e);
				
			}
		}

	}

	private class SocketServerReplyThread extends Thread {

		private Socket hostThreadSocket;
		int cnt;

		SocketServerReplyThread(Socket socket, int c) {
			hostThreadSocket = socket;
			cnt = c;
		}

		@Override
		public void run() {
			OutputStream outputStream;
			String msgReply = "Server, you are #" + cnt;

			try {
				outputStream = hostThreadSocket.getOutputStream();
				PrintStream printStream = new PrintStream(outputStream);
				printStream.print(msgReply);
				printStream.close();

				message +=  msgReply + "\n";

				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						activity.appendLog(message);
					}
				});

			} catch (IOException e) {
				Log.e("ERROR", e.getLocalizedMessage(),e);
				message += "Something wrong! " + e.toString() + "\n";
			}

			activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					activity.appendLog(message);
				}
			});
		}

	}


}
