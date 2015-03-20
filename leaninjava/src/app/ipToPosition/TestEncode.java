package ipToPosition;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

public class TestEncode {

	public static void main(String[] args) {
		TestEncode testEncode = new TestEncode();
//		testEncode.test1();
//		testEncode.test2();
		testEncode.test3();
//		testEncode.test4();
	}
	void test4(){
		URL url;
		URLConnection uc;
		try {
			url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js");
			uc = url.openConnection();
			Object content = uc.getContent();
			String contentType = uc.getContentType();
			String contentEncoding = uc.getContentEncoding();
			System.out.println(contentType);
			System.out.println(contentEncoding);
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void test3(){
		URL url;
		URLConnection uc;
		InputStream is;
		try {
			url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=12.130.132.30");
//			url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js");
			uc = url.openConnection();
			is = uc.getInputStream();
			byte[] buf = new byte[1024];
			int count = 0;
			while((count = is.read(buf, 0, 1024)) > 0){
				byte[] content = new byte[count];
				String text = null;
				for(int index = 0; index <count; index ++){
					content[index] = buf[index];
				}
				String info = new String(content);
				//两边包括大括号才能被fastjson识别使用
				text = info.substring(info.indexOf("{"), info.indexOf("}")+1);
				System.out.println(info);
				System.out.println(text);
				JSONObject jsonObject = JSON.parseObject(text);
				String city = (String)jsonObject.get("country");
				System.out.println(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void test2(){
		String s1 = "\u5317\u4eac";
		System.out.println(s1);
		String s2 = new String(s1);
		System.out.println(s2);
	}
	void test1(){
		//将汉字转换为十六进制Unicode表示  
        for (char ch : "中国".toCharArray()) {  
            if (ch > 128) {  
                System.out.print("\\u" + Integer.toHexString(ch));  
            } else {  
                System.out.print(ch);  
            }  
        }  
        String str = "\u4e2d\u56fd\u5b66";  
        System.out.println("\n"+str);//直接打印出汉字  
          
        str = "\\u5b66\\u56fd\\u5b66";  
        System.out.println(str);// 打印结果为\u5b66\u56fd\u5b66  
          
        //将Unicode字符串转换为汉字输出  
        String s[]=str.split("\\\\u");  
        String t="";  
        for(int j=1;j<s.length;j++){  
            int ab=Integer.valueOf(s[j],16);//先将16进制转换为整数  
            char ac=(char)ab;//再将整数转换为字符  
            System.out.println(ac);  
            t=t+ac;  
        }  
        System.out.println("t:"+t);  
  
        char a=20320;  
        int b=(int)a;   
        System.out.println(a+","+b); 
	}
}
