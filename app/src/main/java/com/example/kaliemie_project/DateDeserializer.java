package com.example.kaliemie_project;

import java.util.Date;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.reflect.Type;


public class DateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        String date = element.getAsString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
