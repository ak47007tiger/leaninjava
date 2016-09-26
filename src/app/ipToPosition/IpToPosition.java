package ipToPosition;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * exmple
 * {"ret":1,"start":"12.130.132.0","end":"12.130.132.255","country":"\u7f8e\u56fd","province":"\u52a0\u5229\u798f\u5c3c\u4e9a","city":"Tracy","district":"","isp":"","type":"","desc":""}
 * @author Administrator
 *
 */
public class IpToPosition {
	final static String funcUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json"; 
	private JSONObject jsonObject;
	public void setIp(String ip){
		URL url;
		URLConnection uc;
		InputStream is;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(funcUrl + ip);
			uc = url.openConnection();
			is = uc.getInputStream();
			byte[] buf = new byte[1024];
			int count = 0;
			while((count = is.read(buf, 0, 1024)) > 0){
				byte[] content = new byte[count];
				for(int index = 0; index <count; index ++){
					content[index] = buf[index];
				}
				sb.append(new String(content));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//两边包括大括号才能被fastjson识别使用
		String text = sb.toString();
		text = text.substring(text.indexOf('{'),text.lastIndexOf('}') + 1);
		System.out.println(text);
		jsonObject = JSON.parseObject(text);
	}
	public Object getValue(String name){
		return jsonObject.get(name);
	}
	public static void main(String[] args) {
		IpToPosition ipToPosition = new IpToPosition();
		ipToPosition.setIp("192.168.1.102");
		System.out.println(ipToPosition.getValue("country"));
		System.out.println(ipToPosition.getValue("city"));
	}
}
