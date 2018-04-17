package com.zit.conversion;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeFormatter implements Converter<String,Date> {
    private String pattern;
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public Date convert(String str) {
        SimpleDateFormat sf = new SimpleDateFormat(this.pattern);
        try {
            Date d = sf.parse(str);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
