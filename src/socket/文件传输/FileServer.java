package 文件传输;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

	public void start(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8090);
			while (true) {
				socket = serverSocket.accept();
				FileTrans fileTrans = new FileTrans(socket);
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
		new FileServer().start();
	}
}
