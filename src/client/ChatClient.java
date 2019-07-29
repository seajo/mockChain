package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

	public static void main(String[] args) {
		System.out.println("클라이언트 프로그램 시작!");
		Socket s = null;
		
		try {
			s = new Socket("163.152.163.78",80);
			System.out.println("클라이언트 접속 성공!!");

			BufferedReader keyword = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter monitor = new PrintWriter(System.out);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			
			// 서버로부터 무작위로 전달되는 메시지를 받아서 화면에 출력하는 Thread를 생성하고 시작.
			Thread t = new Thread(new ClientThread(monitor,br));
			t.start();
			
			
			String msg = null;
			while(true) {
				msg = keyword.readLine();
				pr.println(msg);
				pr.flush();
				if( msg.equals("/exit")) {
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
