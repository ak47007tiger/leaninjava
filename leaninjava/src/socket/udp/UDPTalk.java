package udp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ReadOnlyBufferException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UDPTalk extends JFrame{

	JButton jbt_send;
	JButton jbt_bindLoc;
	JTextArea jta_content;
	JTextArea jta_send;
	JTextField jtf_ip;
	JTextField jtf_remotPort;
	JTextField jtf_locPort;
	UDPContext context;
	public UDPTalk() {
		init();
		initChildren();
		addChildren();
		addListeners();
		this.setVisible(true);
	}
	private void addChildren() {
		JLabel jlb_ip = new JLabel("ip:");
		JLabel jlb_remotPort= new JLabel("remotPort:");
		JLabel jlb_locPort = new JLabel("locPort:");
		JPanel connectInfo = new JPanel();
		connectInfo.setLayout(new GridLayout(3, 2));
		connectInfo.add(jlb_ip);
		connectInfo.add(jtf_ip);
		connectInfo.add(jlb_remotPort);
		connectInfo.add(jtf_remotPort);
		connectInfo.add(jlb_locPort);
		connectInfo.add(jtf_locPort);
		this.add(connectInfo,BorderLayout.NORTH);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(new JScrollPane(jta_content) , BorderLayout.CENTER);
		contentPanel.add(jta_send , BorderLayout.SOUTH);
		this.add(contentPanel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2, 1));
		controlPanel.add(jbt_bindLoc);
		controlPanel.add(jbt_send);
		this.add(controlPanel,BorderLayout.SOUTH);
	}
	private void addListeners() {
		jbt_bindLoc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bindLoc();
			}
		});
		jbt_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
	}
	boolean started = false;
	protected void bindLoc() {
		String locPort = jtf_locPort.getText().trim();
		if(!validataPort(locPort)){
			JOptionPane.showMessageDialog(UDPTalk.this, "本地端口错误");
			return;
		}
		if(context.getPort() == Integer.parseInt(locPort)){
			return;
		}
		context.initSocket(Integer.parseInt(locPort));
		try {
			context.getDsocket().setBroadcast(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if(started){
			return;
		}
		Thread thread = new Thread(new RecAction());
		thread.start();
		started = true;
	}
	protected void send() {
		if(null == context.getDsocket()){
			JOptionPane.showMessageDialog(UDPTalk.this, "尚未绑定本地端口");
			return;
		}
		String s_send = jta_send.getText();
		String ip = jtf_ip.getText().trim();
		String remotPort = jtf_remotPort.getText().trim();
		if(!validataIp(ip) || !validataPort(remotPort)){
			JOptionPane.showMessageDialog(UDPTalk.this, "发送地址错误");
			return;
		}
		try {
			InetAddress inetAddress = InetAddress.getByName(ip);
			int int_remotPort = Integer.parseInt(remotPort , 10);
			DatagramPacket p = new DatagramPacket(s_send.getBytes(), 0, s_send.length(),inetAddress,int_remotPort);
			context.getDsocket().send(p);
			System.out.println(s_send);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean validataIp(String ip){
		if(null == ip){
			return false;
		}
		if(ip.length() == 0){
			return false;
		}
		String regex = "[1-9]{1}[\\d]{0,2}[\\.]{1}[\\d]{0,3}[\\.]{1}[\\d]{0,3}[\\.]{1}[\\d]{0,3}";
		if(!ip.matches(regex)){
			return false;
		}
		return true;
	}
	private boolean validataPort(String port){
		if(null == port){
			return false;
		}
		if(port.length() == 0){
			return false;
		}
		try{
			int int_port = Integer.parseInt(port, 10);
			if(int_port < 0){
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	class RecAction implements Runnable{
		@Override
		public void run() {
			String s_recv = null;
			while(true){
				byte[] buf = new byte[1024];
				DatagramPacket dp_recv = new DatagramPacket(buf, 0, buf.length);  
				try {
					System.out.println("start recv");
					if(context.getDsocket().isClosed()){
						continue;
					}
					context.getDsocket().receive(dp_recv);
					String adress = dp_recv.getAddress().getHostAddress() +":"+ dp_recv.getPort();
					String time = Calendar.getInstance().getTime().toString();
					s_recv = new String(dp_recv.getData(),0,dp_recv.getLength());
					jta_content.append(adress + '\t');
					jta_content.append(time + '\n');
					jta_content.append(' ' + s_recv + '\n');
					System.out.println(s_recv);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void init() {
		this.setLayout(new BorderLayout());
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screen.width;
		int y = screen.height;
		final int width = 400;
		final int height = 300;
		this.setBounds((x - width) / 2, (y - height) / 2, width, height);
		this.setTitle("udpTalk");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initChildren() {
		context = new UDPContext();
		jbt_send = new JButton("send");
		jbt_bindLoc = new JButton("bindLoc");
		
		jta_send = new JTextArea("", 2, 20);
		jta_content = new JTextArea("", 10, 20);
		jta_content.setLineWrap(true);
		
		this.jtf_ip = new JTextField();
		this.jtf_remotPort = new JTextField();
		this.jtf_locPort = new JTextField();
	}
	public static void main(String[] args) {
		new UDPTalk();
	}
}
