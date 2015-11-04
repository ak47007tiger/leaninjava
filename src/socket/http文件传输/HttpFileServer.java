package http文件传输;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpFileServer {

	public void start(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8080);
			while (true) {
				socket = serverSocket.accept();
				HttpFileTrans fileTrans = new HttpFileTrans(socket);
				Thread thread = new Thread(fileTrans);
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
	public static void main(String[] args) {
		new HttpFileServer().start();
	}
}
