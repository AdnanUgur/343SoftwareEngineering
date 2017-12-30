package com.exp;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
public class ZabbixMethods {
	
	 public static JSONObject loginUser(String user, String password) throws JSONException {

	        JSONObject jo = new JSONObject();
	        jo.put("user", user);
	        jo.put("password", password);

	        return method("user.login", jo);
	    }
	 
	
	public static JSONObject loginUser(String user, String password, Boolean userData) throws JSONException {

        JSONObject jo = new JSONObject();
        jo.put("user", user);
        jo.put("password", password);
        jo.put("userData", userData);

        return method("user.login", jo);
    }
	
	//The method allows to retrieve hosts according to the given parameters.
    public static JSONObject getHost(String[] groupids, String auth) throws JSONException {

        JSONObject jo = new JSONObject();
        JSONObject jo2 = new JSONObject();
        
        jo2.put("groupid", groupids);
        jo.put("filter", jo2);
        System.out.println( method("host.get", jo, auth));
        return method("host.get", jo, auth);
        
    }
	
    //This method allows to create new hosts.
    public static JSONObject createHost(String hostName, JSONArray interfaces,  JSONArray groups, String auth) throws JSONException {

        JSONObject jo = new JSONObject();
        jo.put("host", hostName);
        jo.put("interfaces", interfaces);
        jo.put("groups", groups);
        
        
        System.out.println(method("host.create", jo, auth));
        return method("host.create", jo, auth);
//return null;
    }
    
  //This method allows to create new host groups.
    public static JSONObject createHostGroup(String name, String auth) throws JSONException {

        JSONObject jo = new JSONObject();
        jo.put("name", name);

        return method("hostgroup.create", jo, auth);
    }
    // METHOD FUNCTIONS
	
    private static JSONObject method(String methodName, JSONObject params, String auth) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("jsonrpc", "2.0");
        jsonObject.put("method", methodName);
        jsonObject.put("params", params);
        jsonObject.put("auth", auth);
        jsonObject.put("id", "1");

        return jsonObject;
    }

    private static JSONObject method(String methodName, JSONObject params) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("jsonrpc", "2.0");
        jsonObject.put("method", methodName);
        jsonObject.put("params", params);
        jsonObject.put("id", "1");

        return jsonObject;
    }

    private static JSONObject method(String methodName, String[] params, String auth) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("jsonrpc", "2.0");
        jsonObject.put("method", methodName);
        jsonObject.put("params", params);
        jsonObject.put("auth", auth);
        jsonObject.put("id", "1");

        return jsonObject;
    }

    private static JSONObject method(String methodName, String[] params) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("jsonrpc", "2.0");
        jsonObject.put("method", methodName);
        jsonObject.put("params", params);
        jsonObject.put("id", "1");

        return jsonObject;
    }
}

