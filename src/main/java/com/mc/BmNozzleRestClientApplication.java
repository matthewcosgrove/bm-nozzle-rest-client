package com.mc;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.Collection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import com.mc.util.SSLValidationDisabler;

import feign.Feign;
import feign.FeignException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.slf4j.Slf4jLogger;

@SpringBootApplication
@EnableConfigurationProperties(BmNozzleConfigurationProperties.class)
public class BmNozzleRestClientApplication {
	
	@Autowired
	BmNozzleConfigurationProperties bmNozzleConfigurationProperties;

	public static void main(String[] args) {
		SpringApplication.run(BmNozzleRestClientApplication.class, args);
	}
	
	@Bean
	public BmNozzleRestAPIClient bmNozzleApi(BmNozzleTokenClient tokenClient){
		Assert.notNull(tokenClient);
		return Feign.builder()
				.requestInterceptor(tokenSettingInterceptor(tokenClient))
				.target(BmNozzleRestAPIClient.class, bmNozzleConfigurationProperties.getHost());
	}
	
	@Bean
	public BmNozzleTokenClient bmNozzleTokenClient(){
		
		SSLValidationDisabler.dontDoThisAtHome();
    	
    	Decoder headerParsingTokenDecoder = new Decoder() {
			
			@Override
			public String decode(Response response, Type type) throws IOException, DecodeException, FeignException {
				Collection<String> collection = response.headers().get("token");
				String token = collection.stream().findFirst().get();
				return token;
			}
		};
		return Feign.builder()
    			.decoder(headerParsingTokenDecoder)
    			.logger(new Slf4jLogger())
    			.requestInterceptor(usernamePasswordHeaderInterceptor())
    			.target(BmNozzleTokenClient.class, bmNozzleConfigurationProperties.getHost());
    }
	
	@Bean
	public RequestInterceptor tokenSettingInterceptor(BmNozzleTokenClient tokenClient) {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				template.header("token", tokenClient.getToken());
			}
		};
	}
	
    @Bean
	public RequestInterceptor usernamePasswordHeaderInterceptor() {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				template.header("username", bmNozzleConfigurationProperties.getUsername());
				template.header("password", bmNozzleConfigurationProperties.getPassword());
			}
		};
	}
	
}
