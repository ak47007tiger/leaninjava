package 流文件;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class GetAFile {

	public static void main(String[] args) throws Exception {
		URL url = ClassLoader.getSystemResource("source2.txt");
		String filePath = url.getFile();
		System.out.println(filePath);
		InputStream inps = ClassLoader.getSystemResourceAsStream("source2.txt");
		byte[]buf = new byte[8];
		@SuppressWarnings("unused")
		int count;
		while(0 < (count = inps.read(buf))){
			System.out.println(new String(buf,"utf-8"));
		}
		inps.close();
		String source2Path = "E:\\PROJECTS\\leaninjava\\leaninjava\\source1\\source1.txt";
		inps = new FileInputStream(source2Path);
		System.out.println(inps);
		inps.close();
		
		URL url_source3 = ClassLoader.getSystemResource("source3");
		String path_url3 = url_source3.getPath();
		System.out.println(path_url3);
		System.out.println(url_source3.getFile());
		String source3Path = path_url3 + "/source3.txt";
		inps = new FileInputStream(source3Path);
		System.out.println(inps);
		inps.close();
	}
}
