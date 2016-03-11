package https;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class TestHttps {

	static void testConnection() throws MalformedURLException, IOException,
			KeyManagementException, NoSuchAlgorithmException {
		HttpsURLConnection connection;
		/*connection = (HttpsURLConnection) new URL(
				"https://127.0.0.1:8443/webgl/TestServlet").openConnection();
		connection = (HttpsURLConnection) new URL(
				"https://192.168.1.112:20000/").openConnection();*/
		connection = (HttpsURLConnection) new URL(
				"https://tomcat7-testofmars.rhcloud.com/").openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		SSLContext sslc = SSLContext.getInstance("SSL");
		sslc.init(null, null, new SecureRandom());
		SSLSocketFactory sslf = sslc.getSocketFactory();
		connection.setSSLSocketFactory(sslf);
		connection.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		connection.connect();
		connection.getOutputStream().write("this is client".getBytes("utf-8"));
		byte[] b = new byte[512];
		int count;
		String encoding = connection.getContentEncoding();
		System.out.println(encoding);
		if (!(null != encoding && encoding.length() > 0)) {
			encoding = "utf-8";
		}
		InputStream in = connection.getInputStream();
		while (0 < (count = in.read(b))) {
			System.out.print(count + "--" + new String(b, 0, count, encoding));
		}
		in.close();
		connection.disconnect();
	}

	public static void main(String[] args) {
		try {
			testConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
