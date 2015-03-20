package 分析post;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DoRequest implements Runnable {

	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		InputStream in = null;
		OutputStream out = null;

		this.useIns(in, out);
	}

	public void useIns(InputStream in, OutputStream out) {
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			int bufSize = socket.getSendBufferSize();
			byte[] b = new byte[1024 * 512];
			int byteRead = 0;
			int count = 0;
/*			while (0 < (count = in.read(b, byteRead, 1024 * 1024))) {
				byteRead += count;
//				System.out.println(enter == '\n');
			}
*/			
			count = in.read(b,0,450800);
			System.out.println(count);
			out.write("ok".getBytes());
			OutputStream outs = new FileOutputStream("D:\\ok.txt");
			outs.write(b , 0 , 450800);
			outs.flush();
			outs.close();
		} catch (Exception e) {
			try {
				out.write("error".getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
//				socket.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
