package loc.md5;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ByMd5 {
	/**
	 * 
	 * 将指定的字符串用MD5加密
	 * 
	 * @param strIn
	 *            需要加密的字符串
	 * 
	 * @return 加密后的字符串
	 */
	public static String MD5_hexDigits(String strIn, Charset charset, char[]hexDigits) {
		String result = null;
		if (strIn != null) {
			try {
				// 返回实现指定摘要算法的 MessageDigest 对象
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用utf-8编码将originstr字符串编码并保存到source字节数组
				byte[] source = strIn.getBytes(charset);
				// 使用指定的 byte 数组更新摘要
				md.update(source);
				byte[] tmp = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算，结果是一个128位的长整数
				char[] str = new char[32];// 每一个byte带来两个char
				// j表示转换结果中对应的字符位置
				// 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符
				for (int i = 0, j = 0; i < 16; i++) {
					byte b = tmp[i];
					// 取字节中高 4 位的数字转换
					// 无符号右移运算符>>> ，它总是在左边补0
					// 0x代表它后面的是十六进制的数字. f转换成十进制就是15
					str[j++] = hexDigits[b >>> 4 & 0xf];
					// 取字节中低 4 位的数字转换
					str[j++] = hexDigits[b & 0xf];
				}
				result = new String(str);// 结果转换成字符串用于返回
			} catch (NoSuchAlgorithmException e) {
				// 当请求特定的加密算法而它在该环境中不可用时抛出此异常
				e.printStackTrace();
			}
		}
		return result;
	}

	// 用来将字节转换成 16 进制表示的字符
		private final static char hexDigitsY[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
	public final static String MD5_hexDigitsY(String strIn){
		return MD5_hexDigitsY(strIn, Charset.forName("utf-8"));
	}
	public final static String MD5_hexDigitsY(String strIn, String charset){
		return MD5_hexDigitsY(strIn, Charset.forName(charset));
	}
	public final static String MD5_hexDigitsY(String strIn, Charset charset){
		return MD5_hexDigits(strIn, charset, hexDigitsY);
	}
	private final static char hexDigitsX[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	public final static String MD5_hexDigitsX(String s) {
		return ByMd5.MD5_hexDigitsX(s, "utf-8");
	}
	public final static String MD5_hexDigitsX(String s, String charset) {
		return MD5_hexDigitsX(s, Charset.forName(charset));
	}
	public final static String MD5_hexDigitsX(String s, Charset charset) {
		return MD5_hexDigits(s, charset, hexDigitsX);
	}
	public static void main(String[] args) {
		System.out.println(ByMd5.MD5_hexDigitsX("20121221"));
		System.out.println(ByMd5.MD5_hexDigitsX("加密"));
	}
}
