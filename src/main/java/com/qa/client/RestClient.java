package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	//1. We will create a GET Method with out header
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		
		CloseableHttpClient httpClient = HttpClients.createDefault();		
		HttpGet httpget = new HttpGet(url); //http GEt request		
		CloseableHttpResponse closeableHttpResponse =   httpClient.execute(httpget);  //hit the GET url		
		
		return  closeableHttpResponse;
		
	}
	
	
	
	//2. We will create a GET Method with header
	
		public CloseableHttpResponse get(String url, HashMap<String , String > headerMap) throws ClientProtocolException, IOException {
			
			
			CloseableHttpClient httpClient = HttpClients.createDefault();		
			HttpGet httpget = new HttpGet(url); //http GEt request		
			
			
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			
			
			CloseableHttpResponse closeableHttpResponse =   httpClient.execute(httpget);  //hit the GET url		
			
			return  closeableHttpResponse;
			
		}
		
	
	//3. POST Method:
		
		
		public CloseableHttpResponse post(String url , String entityString , HashMap<String ,String> headermap) throws ClientProtocolException, IOException {
			
			CloseableHttpClient httpClient = HttpClients.createDefault();				
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new StringEntity(entityString));
			
			
				for(Map.Entry<String, String> entry : headermap.entrySet()) {
				
					httppost.addHeader(entry.getKey(), entry.getValue());
			}
				
				CloseableHttpResponse closeableHttpResponse = 	httpClient.execute(httppost);
				return closeableHttpResponse;
			
			
		}
		
	

}
