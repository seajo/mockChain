package client;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ClientThread implements Runnable {

	private PrintWriter monitor;
	private BufferedReader br;
	
	public ClientThread(PrintWriter monitor, BufferedReader br) {
		this.monitor = monitor;
		this.br = br;
	}

	@Override
	public void run() {
		String msg = null;
		try {
			while((msg = br.readLine()) != null) {
				monitor.println(msg);
				monitor.flush();
			}			
		} catch (Exception e) {
		}		
	}

}
