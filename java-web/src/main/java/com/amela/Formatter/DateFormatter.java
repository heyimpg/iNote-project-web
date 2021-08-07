package com.amela.Formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateFormatter implements Formatter<LocalDate> {
    private String regex;

    public DateFormatter(String regex) {
        this.regex = regex;
    }

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        try
        {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(regex);
            LocalDate localDate  = LocalDate.parse(text, dateTimeFormatter);
            if (localDate.getYear()<2000)
                throw new IllegalArgumentException("Chỉ cho phép lưu sau năm 2000");
            return localDate;
        }
        catch (DateTimeException e)
        {
            throw new IllegalArgumentException("Vui lòng nhập đúng định dạng yyyy-MM-dd");
        }

    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object.format(DateTimeFormatter.ofPattern(regex));
    }
}
