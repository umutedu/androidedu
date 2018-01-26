package com.bilgeadam.edu.vkfmobil.common.Base;

import android.content.ContentValues;

import com.bilgeadam.edu.vkfmobil.json.Base.ModelTypeStringParser;


/**
 * Created by Umut Boz on 29.09.2015.
 */
public  class BaseServiceRequestParser<T>{


    public  String getRequestByModel(T model)
    {
        String data = "";
        ContentValues fieldDictonaries = new
                ReflectionHelper<T>().getFieldDictionariesByType(model);
        ModelTypeStringParser parser = new ModelTypeStringParser();
        data = parser.getModelTypeToString(fieldDictonaries);
        return data;

    }

}
