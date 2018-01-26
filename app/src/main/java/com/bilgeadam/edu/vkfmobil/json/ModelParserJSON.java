package com.bilgeadam.edu.vkfmobil.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 02483564 on 6.10.2016.
 */
public class ModelParserJSON<T> {

    public T getModelFromJSON(String json, Class<T> clazz) {
        //ResponseAuthenticateModel model =null;
        try {

            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception ex) {
            Log.d("getModelFromJSON JSON PARSER", ex.getMessage());
        }
        return null;
    }


    public List<T> getListModelFromJSON(String json,Class<T> clazz) {
        //ResponseAuthenticateModel model =null;
        try {
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(json).getAsJsonArray();
            Gson gson = new Gson();
            List<T> lst =  new ArrayList<T>();
            for(final JsonElement jsn: array){
                T entity = gson.fromJson(jsn, clazz);
                lst.add(entity);
            }

            return lst;
        } catch (Exception ex) {
            Log.d("getListModelFromJSON JSON PARSER", ex.getMessage());
        }
        return null;
    }

    public String getRequestModelJsonString(T model) {
        String modelString = null;
        try {
            Type type = new TypeToken<T>() {
            }.getType();
            Gson gson = new Gson();
            modelString = gson.toJson(model, type);
        } catch (Exception ex) {
            Log.d("getRequestModelJsonString JSON PARSER", ex.getMessage());
        }
        return modelString;
    }

}
