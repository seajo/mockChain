package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

// Thread에 의해서 공유되어서 사용되어지는 공유객체를 생성하기 위한 class
// Singleton
public class SharedObject {

	private static SharedObject obj = new SharedObject();
	private ArrayList<Socket> clients = new ArrayList<Socket>();
	
	private SharedObject() {
		
	}
	
	public void clientJoin(Socket socket) {
		clients.add(socket);
	}
	
	public static SharedObject getInstance() {
		return obj;
	}

	public void broadcast(String msg) {
		// 전달된 msg를 Arraylist안에 모든 클라이언트 소켓에 대해서 전달.
		try {
			for(Socket s : clients) {
				PrintWriter out = new PrintWriter(s.getOutputStream());
				out.println(msg);
				out.flush();
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
