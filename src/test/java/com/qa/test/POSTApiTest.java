package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.UsersInfo;

import io.qameta.allure.internal.shadowed.jackson.core.exc.StreamWriteException;
import io.qameta.allure.internal.shadowed.jackson.databind.DatabindException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

public class POSTApiTest extends TestBase {
	TestBase testBase;
	String uri;
	String apiurl;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	

	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		url = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
		uri = url + apiurl;
	}
	
	@Test
	public void postApiTest() throws StreamWriteException, DatabindException, IOException
	{	
		
		HashMap<String,String> header=new HashMap<String,String>();
		header.put("content-type", "application/json");
		restClient=new RestClient();
		
		
		//jackSonAPi
		ObjectMapper mapper=new ObjectMapper();
		UsersInfo users=new UsersInfo("morpheus","leader");
		//convert java object to JSON file conversion
		
		mapper.writeValue(new File("C:\\Users\\Nitesh Agrawal\\eclipse-workspace\\RESTApiAutomationSession"
				+ "\\src\\main\\java\\com\\qa\\data\\Users.json"), users);
		
		//Object to JSON in a String 
		String usersJSON=mapper.writeValueAsString(users);
		System.out.println(usersJSON);
		httpResponse=restClient.post(uri, usersJSON, header);
		
		//Status Code 
		
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode,testBase.RESPONSE_STATUS_CODE_201, "Status Code is not 201.");
		
		//JSON String
		
		String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJSON=new JSONObject(responseString);
		System.out.println(responseJSON);
		
		//validate the data we have one read value method 
		
		//JSON to java 
		UsersInfo obj=mapper.readValue(responseString, UsersInfo.class);
		System.out.println(obj.hashCode());
		System.out.println(users.hashCode());
		
		UsersInfo test=users;
		System.out.println(test.hashCode());
		

	}

	}
//is it possible that two object ref variables point to same class object but have diff values of varibales 
