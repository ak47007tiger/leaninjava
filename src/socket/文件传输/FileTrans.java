package 文件传输;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class FileTrans implements Runnable {
	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public FileTrans(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream ins = null;
		OutputStream outs = null;
		try {
			ins = socket.getInputStream();
			URL url = ClassLoader.getSystemResource("server");
			String parentPath = url.getFile();
			File f = new File(parentPath + "/" + System.currentTimeMillis()
					+ ".txt");
			outs = new FileOutputStream(f);

			byte[] b = new byte[1024];
			int count;
			int i = 0;
			while ((count = ins.read(b, 0, 1024)) != -1) {
				outs.write(b, 0, count);
				i++;
			}
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ins) {
					ins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
