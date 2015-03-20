package 文件传输;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetFileFromNet {
	public void get() throws Exception {
		InputStream ins = null;
		OutputStream outs = null;
//		URL url = new URL("http://bbs.chinaunix.net/attachment.php?aid=NDU4MDM5fDkyOWQ3ZjM1fDE0MDI5MTc4Mzl8MHwxOTIxMjcz&fid=223");
		URL url = new URL("http://www.baidu.com");
		URLConnection uc = url.openConnection();
		ins = uc.getInputStream();
		outs = new FileOutputStream(new File("d:\\renee.obj"));
		byte[] b = new byte[1024];
		int count = 0;
		while ((count = ins.read(b, 0, 1024)) > 0) {
			outs.write(b, 0, count);
		}
		ins.close();
		outs.close();
	}

	public static void main(String[] args) {
		try {
			new GetFileFromNet().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
