package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread implements Runnable {

	private Socket socket;    // 클라이언트와 연결된 socket
	private SharedObject obj; // Thread에 의해서 공유되는 공용객체
	private BufferedReader br;
	private PrintWriter pr;
	
	public ChatThread(Socket socket,SharedObject obj) {
		this.socket = socket;
		this.obj = obj;
		this.obj.clientJoin(socket);
	}
	
	@Override
	public void run() {
		// 접속된 클라이언트와 통신하는 코드가 들어오면 되요!
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pr = new PrintWriter(socket.getOutputStream());
			String msg = null;
			while((msg = br.readLine()) != null) {
				if(msg.equals("/exit")) {
					break;
				} else {
					obj.broadcast(msg);
					// 공용객체를 이용해서 broadcast시켜보아요!
					/*pr.println(msg);
					pr.flush();*/
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}


		
		
	}

}
