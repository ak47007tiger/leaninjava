package sslsocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SingleSSLClient {

	public static void main(String[] args) throws KeyManagementException,
			NoSuchAlgorithmException, UnknownHostException, IOException,
			KeyStoreException, CertificateException, UnrecoverableKeyException {
		SSLContext sslc = SSLContext.getInstance("SSL");
		
		String psw = "123123";
		KeyStore ks = KeyStore.getInstance("JKS");
		File file = new File(System.getProperty("user.dir"),
				"/source1/key20151119.jks");
		ks.load(new FileInputStream(file), psw.toCharArray());
		
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, psw.toCharArray());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		tmf.init(ks);
		
		sslc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		SSLSocketFactory sslf = sslc.getSocketFactory();
		SSLSocket socket = (SSLSocket) sslf
				.createSocket("tomcat7-testofmars.rhcloud.com", 8080);
		
		socket.getOutputStream().write(getRequest().getBytes("utf-8"));
		byte[] b = new byte[1024];
		int count;
		while(0 < (count = socket.getInputStream().read(b))){
			System.out.print(new String(b, 0, count, "utf-8"));
		}
		socket.close();
	}
	static String getRequest(){
		String[] content = {
				"GET / HTTP/1.1",
				"Connection: keep-alive",
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
				"User-Agent: Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36",
		};
		return join(content, '\n');
	}
	static String join(String[] array, char join){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < array.length - 1; i++){
			sb.append(array[i]);
			sb.append(join);
		}
		return sb.toString();
	}
}
