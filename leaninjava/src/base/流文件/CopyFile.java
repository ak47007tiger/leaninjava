package 流文件;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class CopyFile {
	public static void main(String[] args) {
		CopyFile coypFile = new CopyFile();
		URL url = null;
		url = ClassLoader.getSystemResource("client");
		String srcPath = url.getFile() + "/" + "test.txt";
		File src = new File(srcPath);
		url = ClassLoader.getSystemResource("server");
		String destPath = url.getFile() + "/" + "a.txt";
		File dest = new File(destPath);
		System.out.println(dest.exists());
		coypFile.copy(dest, src);
	}

	public void copy(File dest, File src) {
		InputStream ins = null;
		OutputStream outs = null;
		try {
			ins = new FileInputStream(src);
			outs = new FileOutputStream(dest);
			byte[] b = new byte[1024];
			int count;
			while (-1 != (count = ins.read(b, 0, 1024))) {
				outs.write(b, 0, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ins)
					ins.close();
				if (null != outs)
					outs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
