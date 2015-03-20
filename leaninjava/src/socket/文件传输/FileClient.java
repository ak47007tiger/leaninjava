package 文件传输;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class FileClient {

	public void post() throws Exception{
		Socket socket = new Socket("localhost", 8090);
		InputStream ins = null;
		OutputStream outs = null;
		
		outs = socket.getOutputStream();
		
		URL url = ClassLoader.getSystemResource("client");
		String parentPath = url.getFile();
		String fileName = "formatfactory.zip";
		ins = new FileInputStream(parentPath + "/" + fileName);
		
		byte[]b = new byte[1024];
		int count;
		while((count = ins.read(b,0,1024)) != -1){
			outs.write(b,0,count);
		}
		ins.close();
		outs.close();
		socket.close();
	}
	public static void main(String[] args) {
		try {
			new FileClient().post();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
