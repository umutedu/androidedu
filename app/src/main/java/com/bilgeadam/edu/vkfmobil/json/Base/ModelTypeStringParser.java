package com.bilgeadam.edu.vkfmobil.json.Base;

import android.content.ContentValues;

import java.util.Map;


public class ModelTypeStringParser {

	public String getModelTypeToString(ContentValues dictionaries)
	{
		String data = "";
        if(dictionaries.size()>0)
        {
        	int dictionariesCount=0;
        	for (Map.Entry<String, Object> entry : dictionaries.valueSet())
			 {
        		dictionariesCount++;

        		data += entry.getKey();
        		if(dictionariesCount!=dictionaries.size())
        		{
        			data += "="+entry.getValue()+"&";
        		}
        		else
        		{
        			data += "="+entry.getValue();
        		}
        		
        				
			 }	
        	
        }
        else
        {
        	data =null;
        }
        
		
		
		
		return data;
	}
}
