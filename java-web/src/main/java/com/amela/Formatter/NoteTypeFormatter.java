package com.amela.Formatter;


import com.amela.Models.NoteType;
import com.amela.Service.INoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class NoteTypeFormatter implements Formatter<NoteType> {

    private INoteTypeService iNoteTypeService;

    @Autowired
    public NoteTypeFormatter(INoteTypeService noteTypeService) {
        this.iNoteTypeService = noteTypeService;
    }



    @Override
    public String print(NoteType object, Locale locale) {
        return "[" + object.getID() + ", " +object.getName() + "]";
    }

    @Override
    public NoteType parse(String text, Locale locale) throws ParseException {
        NoteType noteType = iNoteTypeService.findById(Integer.parseInt(text));
        return noteType;
    }
}
