package 分析post;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
	public void startService(int port) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8070);
			while (true) {
				socket = serverSocket.accept();
				DoRequest doRequest = new DoRequest();
				doRequest.setSocket(socket);
				Thread thread = new Thread(doRequest);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MyServerSocket().startService(8060);
	}
}
