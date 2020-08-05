package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBaseclass {
	
	public int RESPONS_STATUS_COE_200=200;
	public int RESPONS_STATUS_COE_500=500;
	public int RESPONS_STATUS_COE_400=400;
	public int RESPONS_STATUS_COE_401=401;
	public int RESPONS_STATUS_COE_201=201;
	
	public Properties prop;
	
	public TestBaseclass() {
		
		try {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
		System.out.println(ip.toString());
		prop.load(ip);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
