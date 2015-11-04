package 套接字;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
	public void startService(int port) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8060);
			while (true) {
				// �������̴߳���
				socket = serverSocket.accept();
				DoRequest doRequest = new DoRequest();
				doRequest.setSocket(socket);
				Thread thread = new Thread(doRequest);
				thread.start();
			}
		} catch (IOException e) {
			System.err.println("serverSocket����ʧ��");
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("�ر�serverSocket����");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MyServerSocket().startService(8060);
	}
}
