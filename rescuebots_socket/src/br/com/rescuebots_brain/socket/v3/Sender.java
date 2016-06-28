package br.com.rescuebots_brain.socket.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

class Sender extends Thread {
	private ObjectOutputStream mOut;

	public Sender(ObjectOutputStream aOut) {
		mOut = aOut;
	}

	/**
	 * Until interrupted reads messages from the standard input (keyboard) and
	 * sends them to the chat server through the socket.
	 */
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while (!isInterrupted()) {
				String message = in.readLine();
				mOut.writeObject(message);
				mOut.flush();
			}
		} catch (IOException ioe) {
			// Communication is broken
		}
	}
}