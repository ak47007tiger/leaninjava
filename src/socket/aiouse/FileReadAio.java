package aiouse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileReadAio {
	static Object sign = new Object();
	static Queue<String> queue = new LinkedBlockingQueue<String>();
	static ByteBuffer bb = ByteBuffer.allocate(128);
	static int position = 0;
	static CompletionHandler<Integer, ByteBuffer> handler;
	static AsynchronousFileChannel asc;
	public static void main(String[] args) {
		try {
			/*
			 * System.setOut(new PrintStream(Paths.get(
			 * System.getProperty("user.dir"), "source1", "log.txt")
			 * .toFile()));
			 */
			asc = AsynchronousFileChannel.open(Paths.get(
					System.getProperty("user.dir"), "source1", "source1.txt"),
					StandardOpenOption.READ);
			handler = new CompletionHandler<Integer, ByteBuffer>() {
				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					if (result > 0) {
						// call function need data from file
						position += result;
						handlData(attachment);
						queue.add("readNext");
						System.out.println("size of queue:" + queue.size());
						synchronized (sign) {
							System.out.println("notify................");
							sign.notify();
						}
					} else {
						try {
							asc.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					exc.printStackTrace();
				}
			};
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// do something other
		System.out.println(Thread.currentThread().getName());
		new Thread(){
			public void run() {
				try {
					sleep(1000);
					queue.add("readNext");
					synchronized (sign) {
						System.out.println("notify................");
						sign.notify();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		try {
			while (true) {
				synchronized (sign) {
					System.out.println("wait.................");
					sign.wait();
				}
				while(null != queue.poll()){
					System.out.println("read next");
					bb.clear();
					asc.read(bb, position, bb, handler);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static void handlData(ByteBuffer bb) {
//		 CharBuffer cb = Charset.forName("utf-8").decode(bb);
		try {
			System.out.println(new String(bb.array(),0,bb.position(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------");
		System.out.println("read:" + position);
		System.out.println(Thread.currentThread().getName());
		System.out.println(bb.position());
	}

}
