package com.nixalevel.lesson10.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

public class GsonUtil {
    private final Gson gson;

    public Gson getGson() {
        return gson;
    }

    public GsonUtil() {
        gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
    }
}
