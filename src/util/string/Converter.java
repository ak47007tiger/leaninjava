package string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Converter {

	public static void main(String[] args) throws Exception {
		String getenv = System.getProperty("user.dir");
		File unicode = new File(getenv,"source1/unicode.txt");
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(new FileInputStream(unicode), "utf-8"));
		String content = reader.readLine();
		byte[] buf = content.getBytes("utf-8");
		System.out.println(new String(buf,"utf-8"));
		reader.close();
		
	}
}
