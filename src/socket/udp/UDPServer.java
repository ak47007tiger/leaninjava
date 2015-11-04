package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import readConfig.propertiesReader.PropertiesOp;

public class UDPServer{

	public static void main(String[] args) throws Exception {
		PropertiesOp ppop = new PropertiesOp();
		ppop.load(ClassLoader.getSystemResource("udpInfo.properties"));
		int serverPort = Integer.parseInt(ppop.getProperty("serverPort"), 10);
		int clientPort = Integer.parseInt(ppop.getProperty("clientPort"), 10);
		DatagramSocket server = new DatagramSocket(serverPort);
		System.out.println("enter word to be sanded");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		while(!word.equals("end input")){
			server.send(new DatagramPacket(word.getBytes(), 0, word.length(), InetAddress.getLocalHost(), clientPort));
//			server.send(new DatagramPacket(word.getBytes(), 0, word.length(), InetAddress.getByName("10.11.226.178"), clientPort));
			word = sc.nextLine();
		}
		sc.close();
		server.close();
	}
}
