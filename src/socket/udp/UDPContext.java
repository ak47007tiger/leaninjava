package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPContext {

	String ip;
	int port = -1;
	StringBuffer content;
	String remot_ip;
	int remot_port;
	DatagramSocket dsocket;
	DatagramPacket dp_recv;
	DatagramPacket dp_send;
	
	byte[]buf_send;
	byte[]buf_recv;
	
	public byte[] getBuf_send() {
		return buf_send;
	}
	public void setBuf_send(byte[] buf_send) {
		this.buf_send = buf_send;
	}
	public byte[] getBuf_recv() {
		return buf_recv;
	}
	public void setBuf_recv(byte[] buf_recv) {
		this.buf_recv = buf_recv;
	}
	public DatagramPacket getDp_send() {
		return dp_send;
	}
	public void setDp_send(DatagramPacket dp_send) {
		this.dp_send = dp_send;
	}
	public void initSocket(final int port){
		//for port > 0, so no need to know init or not
		//then to know port == this.port
		/*if(port == this.port){
			return;
		}*/
		//not init || this.port != port
		this.port = port;
		try {
			if(null != this.dsocket){
				dsocket.close();
			}
			this.dsocket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	public UDPContext() {
		buf_send = new byte[1024];
		buf_recv = new byte[1024];
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public StringBuffer getContent() {
		return content;
	}
	public void setContent(StringBuffer content) {
		this.content = content;
	}
	public String getRemot_ip() {
		return remot_ip;
	}
	public void setRemot_ip(String remot_ip) {
		this.remot_ip = remot_ip;
	}
	public int getRemot_port() {
		return remot_port;
	}
	public void setRemot_port(int remot_port) {
		this.remot_port = remot_port;
	}
	public DatagramSocket getDsocket() {
		return dsocket;
	}
	public void setDsocket(DatagramSocket dsocket) {
		this.dsocket = dsocket;
	}
	public DatagramPacket getDp_recv() {
		return dp_recv;
	}
	public void setDp_recv(DatagramPacket dp_recv) {
		this.dp_recv = dp_recv;
	}
	
}
