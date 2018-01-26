package com.bilgeadam.edu.vkfmobil.common.Base;

/**
 * Created by ahmet.birdevrim on 31.10.2016.
 */

public enum ServiceType {
   CategoryBl("Category"),
    BookmarkBL("Bookmark"),
    RegionBL("Region");

    public final String Value;
    private ServiceType(String value)
    {
        Value = value;
    }
}
