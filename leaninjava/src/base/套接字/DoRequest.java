package 套接字;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(in));
			
			bufferedReader.mark(1024 * 3);
			char buf[] = new char[1024*3];
			bufferedReader.read(buf);
			String bufstr = new String(buf);
			String bufarray[] = bufstr.split("\n");
			bufferedReader.reset();
			String firstLine = bufarray[0];
			String mid = firstLine.split(" ")[1];
			// ��ʼ������ȷ������
			try{
			if(mid.split("?").length > 1){
					this.calServer(mid.split("?")[1].split("&"), out);
			}else{
				this.fileServer(bufferedReader, out);
			}
			}catch (Exception e) {
				this.fileServer(bufferedReader, out);
			}
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			try {
				socket.close();
				in.close();
				out.close();
			} catch (IOException e) {
				System.err.println("�ر���ʧ��");
			}
		}
	}

	public void calServer(String parms[], OutputStream out) {
		double v1 = new Double(parms[0]);
		double v2 = new Double(parms[2]);
		char option = parms[1].toCharArray()[0];
		double r = 0;
		String msg = "��������";
		switch (option) {
		case '+':r= v1+v2;
			break;
		case '-':r= v1-v2;
			break;
		case '*':r= v1*v2;
			break;
		case '/':r = v1/v2;
			break;
		default:
			msg = Double.toString(r);
			break;
		}
		try {
			out.write(msg.getBytes());
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void fileServer(BufferedReader bufferedReader, OutputStream out) {
		InputStream fileStream = null;
		String head;
		try {
			head = bufferedReader.readLine();
			String heads[] = head.split(" ");
			String path = heads[1];
			String rootPath = ClassLoader.getSystemResource("").getPath();
			rootPath = rootPath.substring(0, rootPath.lastIndexOf("/") - 4);
			String realPath = rootPath + path;

			byte[] writeBuf = new byte[1024 * 4];
			fileStream = new FileInputStream(realPath);
			while (-1 != fileStream.read(writeBuf)) {
				out.write(writeBuf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
