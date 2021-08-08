package com.amela.Controllers;

import com.amela.Models.Login;
import com.amela.Models.Note;
import com.amela.Models.NoteType;
import com.amela.Service.INoteService;
import com.amela.Service.INoteTypeService;
import org.hibernate.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ManageNoteController {
    @Autowired
    private INoteService noteService;
    @Autowired
    private INoteTypeService noteTypeService;

    @ModelAttribute("note_types")
    public List<NoteType> initNoteTypeList()
    {
        return noteTypeService.findAll();
    }

   /* @ModelAttribute("notes")
    public List<Note> initNoteList()
    {
        return noteService.findAll();
    }*/

//List
    @GetMapping("/manage-note")
    public ModelAndView index(@RequestParam("searchView")Optional<String> search,
                              @PageableDefault(value = 5) Pageable pageable,
                              @SessionAttribute("login")Login login) throws Exception
    {

        ModelAndView modelAndView = new ModelAndView("note/manage-note");
        if (search.isPresent())
        {
            modelAndView.addObject("searchResult", search.get());
            modelAndView.addObject("notes", noteService.findByTitle(search.get(), pageable) );
        }
        else
            modelAndView.addObject("notes", noteService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/notes-by-type/{id}")
    public ModelAndView getNoteByType(@PathVariable("id")int ID, @RequestParam("searchView")Optional<String> search, Pageable pageable)
    {
        ModelAndView modelAndView = new ModelAndView("note/manage-note");
        modelAndView.addObject("note_type", noteTypeService.findById(ID));
        Page<Note> noteList = noteService.findByType(ID, pageable);
        if (search.isPresent())
        {
            List<Note> noteList_temp = new ArrayList<>();
            for (Note note_item : noteList)
            {
                if (note_item.getTitle().toLowerCase(Locale.ROOT).contains(search.get().toLowerCase(Locale.ROOT)))
                    noteList_temp.add(note_item);
            }
            int start = (int)pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), noteList_temp.size());
            Page<Note> pageList_temp = new PageImpl<>(noteList_temp.subList(start, end), pageable, noteList_temp.size());
            modelAndView.addObject("notes", pageList_temp);
        }
        else
            modelAndView.addObject("notes", noteList);

        return modelAndView;
    }

}
