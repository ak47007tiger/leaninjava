package aiouse;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileReadAio0 {

	static int position = 0;
	public static void main(String[] args) throws Exception {
		final AsynchronousFileChannel afc = AsynchronousFileChannel.open(Paths.get(
				System.getProperty("user.dir"), "source1", "source1.txt"),
				StandardOpenOption.READ);
		final ByteBuffer dst = ByteBuffer.allocate(512);
		afc.read(dst, position, dst, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				if (result > 0) {
					// call function need data from file
					position += result;
					System.out.print(new String(attachment.array(),0,attachment.position()));
					dst.clear();
					afc.read(dst, position, dst, this);
				}else{
					try {
						afc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
			}
		});
	}
}
