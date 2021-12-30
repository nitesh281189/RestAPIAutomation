package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestClient {
	// Get method without headers 
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// It will create one Get connection with URL
		HttpGet httpget = new HttpGet(url);// http get request
		// It will send the CloseableHttpresponse Object
		CloseableHttpResponse httpResponse = httpClient.execute(httpget);// hit the Get URL
		return httpResponse;
	}
	
	// Get method with headers information in the get call  
		public CloseableHttpResponse get(String url,HashMap<String,String> header) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// It will create one Get connection with URL
			HttpGet httpget = new HttpGet(url);// http get request
			
			for (Map.Entry<String, String> h:header.entrySet())
			{
				httpget.addHeader(h.getKey(),h.getValue());
			}
			// It will send the CloseableHttpresponse Object
			CloseableHttpResponse httpResponse = httpClient.execute(httpget);// hit the Get URL
			return httpResponse;
		}


			//Post Method
		
			
		public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerInfo) throws ClientProtocolException, IOException
		{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);// http post request
			
			//for pay Load
			try {
				httppost.setEntity(new StringEntity(entityString));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			//for headers 
			for(Map.Entry<String, String> entry:headerInfo.entrySet())
			httppost.addHeader(entry.getKey(),entry.getValue());
		
			CloseableHttpResponse httpResponse=httpClient.execute(httppost);
			return httpResponse;
		
		
		
		}
		
		



}
	
	
	
	
	
	
	
	
	
	
	
	

