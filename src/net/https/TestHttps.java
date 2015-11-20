package https;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class TestHttps {

	static void testConnection() throws MalformedURLException, IOException, KeyManagementException, NoSuchAlgorithmException{
		HttpsURLConnection connection = (HttpsURLConnection) new URL(
				"https://127.0.0.1:8443/webgl/TestServlet").openConnection();
		SSLContext sslc = SSLContext.getInstance("SSL");
		sslc.init(null, new TrustManager[]{new X509TrustManager() {
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				
			}
			
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				
			}
		}}, new SecureRandom());
		SSLSocketFactory sslf = sslc.getSocketFactory();
		connection.setSSLSocketFactory(sslf);
		connection.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		connection.connect();
		byte[] b = new byte[512];
		int count;
		String encoding = connection.getContentEncoding();
		System.out.println(encoding);
		if(!(null != encoding && encoding.length() > 0)){
			encoding = "utf-8";
		}
		while(0 < (count = connection.getInputStream().read(b))){
			System.out.print(count +"--"+ new String(b,0,count,encoding));
		}
		connection.getInputStream().close();
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
