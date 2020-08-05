package com.qa.test;

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

import com.qa.base.TestBaseclass;
import com.qa.client.RestClient;
import com.qa.utill.TestUtill;

public class GetAPITest extends TestBaseclass{

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
	public void tets() throws ClientProtocolException, IOException {
		
	    restClient = new RestClient();
	 	closeableHttpResponse = restClient.get(url);
		
		//i. Sattus code:
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();		
		System.out.println("Status  code : " + statuscode);	
		
		Assert.assertEquals(statuscode , RESPONS_STATUS_COE_200 , "Sattus code isnot 200");
		
		//ii. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");		
		System.out.println("Response string : " + responseString);
		
		JSONObject responseJson = new JSONObject(responseString);		
		System.out.println("Response JSON from API : " + responseJson);
		
		
		//Single value assertion
	     String  s= TestUtill.getValueByJPath(responseJson , "/per_page"); 
	     System.out.println("value per page:" + s);
    	 Assert.assertEquals(Integer.parseInt(s), 6);
 	 
	     String  totalvlaue= TestUtill.getValueByJPath(responseJson , "/total"); 
	     System.out.println("value per page:" + totalvlaue);
	     Assert.assertEquals(totalvlaue, "12");
	     
	     
	     
	    String lastname =  TestUtill.getValueByJPath(responseJson, "/data[0]/last_name");
	    String id =  TestUtill.getValueByJPath(responseJson, "/data[0]/id");
	    String avatar =  TestUtill.getValueByJPath(responseJson, "/data[0]/avatar");
	    String firstname =  TestUtill.getValueByJPath(responseJson, "/data[0]/first_name");
	    
	    System.out.println(lastname);
	    System.out.println(id);
	    System.out.println(avatar);
	    System.out.println(firstname);
	    
	 
	    
		//iii. ALL headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Header Array: " + allHeaders);
		
		
		
		
	}
	
	
	@Test
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
	
		RestClient restClient = new RestClient();
		HashMap<String , String> headerMap= new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Transfer-Encoding", "chunked");
		
		closeableHttpResponse = restClient.get(url, headerMap);
		
		//i. Sattus code:
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();		
		System.out.println("Status  code : " + statuscode);	
		
		Assert.assertEquals(statuscode , RESPONS_STATUS_COE_200 , "Sattus code isnot 200");
		
		//ii. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");		
		System.out.println("Response string : " + responseString);
		
		JSONObject responseJson = new JSONObject(responseString);		
		System.out.println("Response JSON from API : " + responseJson);
		
		
		//Single value assertion
	     String  s= TestUtill.getValueByJPath(responseJson , "/per_page"); 
	     System.out.println("value per page:" + s);
    	 Assert.assertEquals(Integer.parseInt(s), 6);
 	 
	     String  totalvlaue= TestUtill.getValueByJPath(responseJson , "/total"); 
	     System.out.println("value per page:" + totalvlaue);
	     Assert.assertEquals(totalvlaue, "12");
	     
	     
	     
	    String lastname =  TestUtill.getValueByJPath(responseJson, "/data[0]/last_name");
	    String id =  TestUtill.getValueByJPath(responseJson, "/data[0]/id");
	    String avatar =  TestUtill.getValueByJPath(responseJson, "/data[0]/avatar");
	    String firstname =  TestUtill.getValueByJPath(responseJson, "/data[0]/first_name");
	    
	    System.out.println(lastname);
	    System.out.println(id);
	    System.out.println(avatar);
	    System.out.println(firstname);
	    
	 
	    
		//iii. ALL headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Header Array: " + allHeaders);
		
		
		
		
	}
	
	
	
	
	
}
