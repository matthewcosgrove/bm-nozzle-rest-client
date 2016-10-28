package com.mc.util;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLValidationDisabler {
	
	public static void dontDoThisAtHome() {
		System.err.println("WARNING: Skipping SSL Validation");
		try {
		    SSLContext sc = SSLContext.getInstance("SSL"); 
		    sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);
		} catch (GeneralSecurityException e) {
		}
	}
	
	// Create a trust manager that does not validate certificate chains
	private static TrustManager[] trustAllCerts = new TrustManager[] { 
	    new X509TrustManager() {     
	        public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
	            return new X509Certificate[0];
	        } 
	        public void checkClientTrusted( 
	            java.security.cert.X509Certificate[] certs, String authType) {
	            } 
	        public void checkServerTrusted( 
	            java.security.cert.X509Certificate[] certs, String authType) {
	        }
	    } 
	};

}