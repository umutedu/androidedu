package com.bilgeadam.edu.vkfmobil.common.Base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {
	
	/**
	 * 
	 * @param jsonString mevcut json stringdir. { "a" : "xyz"  }
	 * @param newValue  yeni json string dir { "b" :  "ksa" } 
	 * @param keyValue  eklenecek child anahtarıdır "C" 
	 * @return sonuc olarak {   "a" : "xyz" C:{ "b" :  "ksa" }  }  doner. JSON arrayler icinde gecerlidir. 
	 */
	public static String insertChildNodetoJsonString( String jsonString, String newValue, String keyValue ){
	
			String result = jsonString ;
			result = jsonString.substring(0, jsonString.lastIndexOf('}')) + ",\"" + keyValue + "\":" + newValue + "}" ;
			
			return result ;
	}
	
	public static String addFirstTagtoJsonString(String jsonString, String keyValuetoAdd) throws JSONException
	{
		
		//JSONObject jsonObjectWithTag = new JSONObject();
		//jsonObjectWithTag.put(keyValuetoAdd, jsonString);
		//return jsonObjectWithTag.toString() ;
		return "{" + keyValuetoAdd + ":" + jsonString + "}";
	}

	public static  boolean isJSONValid(String json) {
	    try {
	        new JSONObject(json);
	    } catch (JSONException ex) {
	        
	        try {
	            new JSONArray(json);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}
}
