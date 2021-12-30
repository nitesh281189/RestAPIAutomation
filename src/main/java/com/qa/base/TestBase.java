package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public static Properties prop;
	public static FileInputStream fs;
	public int RESPONSE_STATUS_CODE_200=200;
	public int RESPONSE_STATUS_CODE_400=400;
	public int RESPONSE_STATUS_CODE_500=500;
	public int RESPONSE_STATUS_CODE_404=404;
	public int RESPONSE_STATUS_CODE_201=201;
	
	
	
	public TestBase() 
	{	
		prop=new Properties();
		
		try {
			fs = new FileInputStream("C:\\Users\\Nitesh Agrawal\\eclipse-workspace\\RESTApi\\src\\main\\java\\com\\qa\\config\\config.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
}
