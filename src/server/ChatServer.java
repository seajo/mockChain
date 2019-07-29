package server;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		
		System.out.println("서버시작!");
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(80);
			while(true) {
				Socket s = server.accept();
				// 접속한 클라이언트와 실제 통신을 진행하는 Thread instance를 생성
				SharedObject obj = SharedObject.getInstance();
				// Thread에 의해서 공유되는 공용객체를 얻어와서 Thread에게 할당
				Thread t = new Thread(new ChatThread(s,obj));
				t.start();
			}			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
