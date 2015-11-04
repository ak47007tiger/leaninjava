package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveAll {

	public static void main(String[] args){
		DatagramSocket rec = null;
		try {
			rec = new DatagramSocket(8987);
			int length = 2048;
			byte[]buf = new byte[length];
			DatagramPacket dp = new DatagramPacket(buf, length);
			while(true){
				rec.receive(dp);
				System.out.println(dp.getPort());
				System.out.println(new String(dp.getData(),0,dp.getLength()));
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != rec){
				rec.close();
			}
		}
	}
}
