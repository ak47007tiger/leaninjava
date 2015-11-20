package niosocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import singleclassutil.SimpleLogger;

public class FirstNoiServer {

	ServerSocketChannel ssc = null;
	Selector selector = null;
	final static int port = 8088;
	public FirstNoiServer() {
		init();
	}
	public void init(){
		try {
			selector = Selector.open();
			ssc = ServerSocketChannel.open();
			ssc.socket().setReuseAddress(true);
			ssc.configureBlocking(false);
			ssc.socket().bind(new InetSocketAddress("10.1.201.216",port));
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			SimpleLogger.getLogger("system").log("server start");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void service(){
		Set<SelectionKey> keys;
		Iterator<SelectionKey> iterator;
		SelectionKey sk = null;
		int i = 0;
		int r = 0;
		int w = 0;
		int s;
		try {
			while((s = selector.select()) > 0){
				try{
					keys = selector.selectedKeys();
					iterator = keys.iterator();
					SimpleLogger.getLogger("s").log(s+"\n");
					while(iterator.hasNext()){
						SimpleLogger.getLogger("i").log("i:" + ++i + "\n");
						sk = iterator.next();
						iterator.remove();
						if(sk.isAcceptable()){
							doAccept(sk);
						}
						if(sk.isReadable()){
							SimpleLogger.getLogger("r").log("r:" + ++r + "\n");
							doRead(sk);
						}
						if(sk.isWritable()){
							doWrite(sk);
							SimpleLogger.getLogger("w").log("w:" + ++w + "\n");
						}
					}
				}catch(IOException e){
					e.printStackTrace();
					SimpleLogger.getLogger("system").logln("con" + e.getMessage());
					if(null != sk){
						sk.cancel();
						sk.channel().close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			SimpleLogger.getLogger("system").logln("select" + e.getMessage());
		}
	}
	public void doAccept(SelectionKey sk) throws IOException{
		ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
		SocketChannel sc = ssc.accept();
		Socket client = sc.socket();
		SimpleLogger.getLogger("system").logln("accept " + client.getInetAddress().getHostAddress() + ":" + client.getPort());
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, (Integer)GlobalVal.NOTWRITE);
	}
	public void doRead(SelectionKey sk) throws IOException{
		SocketChannel sc = (SocketChannel) sk.channel();
		System.out.println(sc.isConnected());
		ByteBuffer dst = ByteBuffer.allocate(128);
		int count = sc.read(dst);
		SimpleLogger.getLogger("system").logln("read count:" + count);
		String recvStr = new String(Arrays.copyOfRange(dst.array(), 0, count),"utf-8");
		SimpleLogger.getLogger("system").logln("read content:" + recvStr);
		sk.attach((Integer)GlobalVal.SENDTIME);
		if(endConnect.equals(recvStr)){
			Socket client = sc.socket();
			SimpleLogger.getLogger("system").logln("closing..." + client.getInetAddress().getHostAddress() + ":" + client.getPort());
			sk.cancel();
			sc.close();
			SimpleLogger.getLogger("system").logln("closed");
			sk.attach((Integer)GlobalVal.SOCKETCLOSE);
		}
	}
	final static String endConnect = "endConnect";
	public void doWrite(SelectionKey sk) throws IOException{
		int op = (Integer)sk.attachment();
		switch (op) {
		case GlobalVal.SOCKETCLOSE:
			break;
		case GlobalVal.NOTWRITE:
			return;
		case GlobalVal.SENDTIME:
			SocketChannel sc = (SocketChannel) sk.channel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String time = sdf.format(new Date()) + "\n";
			ByteBuffer src = ByteBuffer.wrap(time.getBytes("utf-8"));
			while(src.hasRemaining()){
				sc.write(src);
			}
		}
	}
	public static void main(String[] args) {
		new FirstNoiServer().service();
	}
}
