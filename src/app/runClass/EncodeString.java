package runClass;

import java.io.UnsupportedEncodingException;

public class EncodeString {

	public static String getByEncode(String value,String sourceCharset,String destCharset){
		String result = null;
		try {
			result = new String(value.getBytes(sourceCharset),destCharset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
