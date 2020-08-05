package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBaseclass;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBaseclass {
	

	TestBaseclass testBase;
	String serviceUrl;
	String apiurl;
	String url;
	RestClient restClient;
	CloseableHttpResponse    closeableHttpResponse;
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {
	
		testBase = new TestBaseclass();
		
		 serviceUrl = prop.getProperty("URL");
		 apiurl = prop.getProperty("serviceURL");
		
		
		 url = serviceUrl + apiurl;
		
		
		
	
	}
	
	
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();

		HashMap<String , String> headerMap= new HashMap<String, String>();
		headerMap.put("Content-Type", "text/plain");
		
		// How to create JSON (Users.java)
		//Jackson API
		
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("morpheus","leader");
		
		//Object to JSON file conversion:
		
		mapper.writeValue(new File("C://Users//arifs3//eclipse-workspace//restapi//src//main//java//com//qa//data//users.json"), user);
		
		
		//Object to JSON String
		
		String userJsonString = mapper.writeValueAsString(user);
		System.out.println(userJsonString);
		
		closeableHttpResponse = restClient.post(url, userJsonString, headerMap);
		
		//1. Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, RESPONS_STATUS_COE_201);
		
		//2. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is : " + responseJson);

		Users userResObj = mapper.readValue(responseString, Users.class);
		System.out.println(userResObj);
		
//		Assert.assertTrue(user.getName().equals(userResObj.getName()));
//		Assert.assertTrue(user.getJob().equals(userResObj.getJob()));
		
	}
	
	
	
	
	
	
	
	
	

}
