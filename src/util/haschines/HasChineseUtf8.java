package haschines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class HasChineseUtf8 implements HasChinese{
	
	@Override
	public boolean hasChinese(File file) throws IOException {
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"uff-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		int length;
		if(Integer.MAX_VALUE > file.length() / 4){
			length = (int) (file.length() / 4);
		}else{
			length = Integer.MAX_VALUE;
		}
		char[] cbuf = new char[length];
		int count;
		boolean result = false;
		while(-1 != (count = bufferedReader.read(cbuf))){
			if(hasChinese(cbuf, 0, count)){
				result = true;
				break;
			}
		}
		bufferedReader.close();
		return result;
	}

	@Override
	public boolean hasChinese(char[] cbuf,int start,int length) {
		for(int i = start; i < start + length; i++){
			char c = cbuf[i];
			if(0x4e00 <= c && c <= 0x9fa5){
				return true;
			}
		}
		return false;
	}

}
