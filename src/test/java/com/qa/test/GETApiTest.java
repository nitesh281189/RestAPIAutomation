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

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GETApiTest extends TestBase {

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

	@Test(priority=1)
	public void getApiTestWithoutHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		httpResponse = restClient.get(uri);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200, "Status Code is not 200.");

		// JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("ResponseJSONfromAPI:" + responseJson);
		String per_page = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("The values per page is " + per_page);
		Assert.assertEquals(Integer.parseInt(per_page), 6);

		// get the value from JSON array

		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		System.out.println(lastName);

		// above method is not good in Java. Need to learn the parsing of the JSON to
		// JAVA and vice versa

		// Response Headers
		Header[] headerarray = httpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerarray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array" + allHeaders);
	}

	@Test(priority=2)
	public void getApiTestWithHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String,String> headerInfo=new HashMap<String,String>();
		headerInfo.put("content-type", "application/json");
		
		httpResponse = restClient.get(uri,headerInfo);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200, "Status Code is not 200.");

		// JSON String
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("ResponseJSONfromAPI:" + responseJson);
		String per_page = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("The values per page is " + per_page);
		Assert.assertEquals(Integer.parseInt(per_page), 6);

		// get the value from JSON array

		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		System.out.println(lastName);

		// above method is not good in Java. Need to learn the parsing of the JSON to
		// JAVA and vice versa

		// Response Headers
		Header[] headerarray = httpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerarray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array" + allHeaders);
	}

}


