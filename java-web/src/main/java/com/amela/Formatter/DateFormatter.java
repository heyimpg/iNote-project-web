package com.amela.Formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class DateFormatter implements Formatter<String> {

    @Override
    public String parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(String object, Locale locale) {
        String[] datetimes = object.split("/");
        String date = datetimes[0];
        String month = datetimes[1];
        String year = datetimes[2];


        return null;
    }
}
