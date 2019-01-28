package viseco.sc.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

@SuppressWarnings("deprecation")
public class Util {
	/**
	 * @param n/a
	 * @return Trusted SSL HTTPAsyncClient (for testing only) 
	 */
	static CloseableHttpAsyncClient trustedHTTPClient() {
		SSLContext sslcontext = null;
		// do not check certificate (for testing only)
		try {
			sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
			            @Override
			            public boolean isTrusted(
			                    final X509Certificate[] chain,
			                    final String authType) throws CertificateException {
			                return true;
			            }
			        }).build();
		} catch (KeyManagementException e1) {
			
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			
			e1.printStackTrace();
		}
		// do not check hostname (for testing only)
		X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		CloseableHttpAsyncClient client = HttpAsyncClients.custom().setSSLContext(sslcontext).setHostnameVerifier(hostnameVerifier).build();
		
		
		return client; 		
	}
	
	/**
	 * 
	 */
	static String formatDateTime(String data){
		Date in;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			in = formatter.parse(data);
		}
		catch (ParseException e) {
			return formatter.format(new Date());
		}
		return formatter.format(in);
	}
	
	/**
	 * 
	 */
	static boolean isDate(String data){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (data.length() == 10) { 
			try {
				formatter.parse(data);
			}
			catch (ParseException e) {
				return false;
			}
			return true;
		}
		else
			return false;
	}
	
	static boolean isDateTime(String data){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (data.equals("")) return true; // allow null date time
		
		if (data.length() == 19) { 
			try {
				formatter.parse(data);
			}
			catch (ParseException e) {
				return false;
			}
			return true;
		}
		else
			return false;
	}
}
