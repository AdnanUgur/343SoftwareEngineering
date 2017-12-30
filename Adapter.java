package com.exp;

import org.mule.api.annotations.param.Payload;

import groovy.json.JsonParser;

import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PutMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Adapter {
	
	private static String auth;
	private static String response;
	private static String ZABBIX_API_URL = "http://localhost/zabbix/api_jsonrpc.php"; // http://localhost/zabbix/api_jsonrpc.php URL
	
	
    public static String deneme(@Payload String input) throws JSONException, FileNotFoundException, UnsupportedEncodingException, ParseException{
    	
    	/*System.out.println(input);
        Converter converter_obj = new Converter(input); 
        converter_obj.JSONtoXML();
        
        
        return converter_obj.getOutput();*/
    	
        testClient();
        return null;
    	
    }
    public static InputStream fromString(String str) throws UnsupportedEncodingException {
		byte[] bytes = str.getBytes("UTF-8");
		return new ByteArrayInputStream(bytes);
	}
    
    public static void testClient() throws JSONException, FileNotFoundException, UnsupportedEncodingException, ParseException {
    	
		HttpClient client = new HttpClient();
		
		String [] hostarr = new String[1];
		hostarr[0] = "Zabbix2";
		
		PutMethod putMethod = new PutMethod(ZABBIX_API_URL);
		PutMethod putMethod2 = new PutMethod(ZABBIX_API_URL);
		
		//putMethod.setRequestHeader("Content-Type", "application/json-rpc"); // content-type is controlled in api_jsonrpc.php, so set it like this
		putMethod2.setRequestHeader("Content-Type", "application/json-rpc"); 
		
		//putMethod.setRequestBody(fromString(ZabbixMethods.loginUser("Admin", "zabbix").toString())); 
		
		JSONObject element = new JSONObject();
		element.put("type", "1");
		element.put("main", "1");
		element.put("useip", "1");
		element.put("ip", "10.1.90.58");
		element.put("dns", "");
		element.put("port", "10050");
		JSONArray  interfaces = new JSONArray();
		interfaces.put(element);
		
		JSONObject element2 = new JSONObject();
		element2.put("groupid", "16");
		JSONArray group = new JSONArray();
		group.put(element2);
		String[] arr = new String[1];
		arr[0] = "16";
		putMethod2.setRequestBody(fromString(ZabbixMethods.getHost(arr, "3c4a9b74964439fa8c5252df74b778df").toString())); 
		//putMethod2.setRequestBody(fromString(ZabbixMethods.createHost("DenemeHost2", interfaces, group, "3c4a9b74964439fa8c5252df74b778df").toString())); 
		//putMethod2.setRequestBody(fromString(ZabbixMethods.createHostGroup("denemeGroup", "3c4a9b74964439fa8c5252df74b778df").toString())); 
		String loginResponse = "";
		String GetResponse = "";
		
		try {
			//client.executeMethod(putMethod);
			client.executeMethod(putMethod2);// send to request to the zabbix api
			
			//loginResponse = putMethod.getResponseBodyAsString(); // read the result of the response
			GetResponse = putMethod2.getResponseBodyAsString();
			
			/*JSONObject jsonobj = new JSONObject(loginResponse);
			auth = (String) jsonobj.get("result");*/
			
			
			JSONObject deneme = new JSONObject(GetResponse);
			response = (String) deneme.get("jsonrpc");
			
			
			//System.out.println("Auth: "  + auth);
			System.out.println("KKKK: "  + response);
			//System.out.println("Zabbix server response: \n"+loginResponse); // print the result of the response
			System.out.println("Zabbix server response get_host: \n"+GetResponse); // print the result of the response
			
			
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
    
    
    

}
	
