package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import readConfig.propertiesReader.PropertiesOp;

public class UDTClient {

	public static void main(String[] args) throws Exception {
		PropertiesOp ppop = new PropertiesOp();
		ppop.load(ClassLoader.getSystemResource("udpInfo.properties"));
		int clientPort = Integer.parseInt(ppop.getProperty("clientPort"), 10);
		DatagramSocket client = new DatagramSocket(clientPort);
		byte[]buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		while(true){
			client.receive(dp);
			System.out.print("receive from server:");
			System.out.println(new String(dp.getData(),0,dp.getLength()));
		}
	}
}
