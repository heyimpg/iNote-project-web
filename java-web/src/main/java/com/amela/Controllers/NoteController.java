package com.amela.Controllers;

import com.amela.Models.Note;
import com.amela.Models.NoteType;
import com.amela.Service.INoteService;
import com.amela.Service.INoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NoteController {
    @Autowired
    private INoteService noteService;
    @Autowired
    private INoteTypeService noteTypeService;

    @ModelAttribute("note")
    public Note initNote()
    {
        return new Note();
    }

    @ModelAttribute("note_types")
    public List<NoteType> initNoteTypeList()
    {
        return noteTypeService.findAll();
    }

    @ModelAttribute("notes")
    public List<Note> initNoteList()
    {
        return noteService.findAll();
    }

//List
    @GetMapping("/home")
    public String index()
    {
        return "note/home";
    }
//Create
    @PostMapping("/home")
    public String create(@Valid @ModelAttribute("note") Note note, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors())
            return "note/home";
        else
        {
            noteService.save(note);
            redirectAttributes.addFlashAttribute("message", "Đã thêm mới 1 ghi chú thành công!");
            return "redirect:/home";
        }
    }
//Detail
    @GetMapping("/home/{id}")
    public ModelAndView detail(@PathVariable("id") int ID)
    {
        ModelAndView modelAndView = new ModelAndView("note/detail");
        Note note = noteService.findById(ID);
        modelAndView.addObject("note_detail", note);
        return modelAndView;
    }
    @PostMapping("/home/{id}")
    public String detailAction(@RequestParam("action") String action, @PathVariable("id") int ID)
    {
        if (action.equals("edit"))
            return "redirect:/edit-note/"+ID;
        else
            return "redirect:/delete-note/"+ID;
    }

//Edit
    @GetMapping("/edit-note/{id}")
    public ModelAndView edit(@PathVariable("id") int ID) {
        ModelAndView modelAndView = new ModelAndView("note/edit");
        Note note = noteService.findById(ID);
        modelAndView.addObject("note_edit", note);
        return modelAndView;
    }
    @PostMapping("/edit-note-accept")
    public String editAccept(@Valid @ModelAttribute("note_edit") Note note, BindingResult bindingResult, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
       if (action.equals("save"))
       {
           if (bindingResult.hasFieldErrors())
           {
               return "note/edit";
           }
           else
           {
               noteService.save(note);
               redirectAttributes.addFlashAttribute("message", "Lưu thành công!");
               return "redirect:/edit-note/"+note.getID();
           }

       }
       else
           return "redirect:/manage-note";
//           return "redirect:/home/"+note.getID();
    }

//Delete
    @GetMapping("/delete-note/{id}")
    public String delete(@PathVariable("id") int ID, Model model)
    {
        Note note = noteService.findById(ID);
        model.addAttribute("note_delete", note);
        return "note/delete";
    }
    @PostMapping("/delete-note/{id}")
    public String delete(@PathVariable("id") int ID, @RequestParam("action") String action, RedirectAttributes redirectAttributes)
    {
        if (action.equals("confirm"))
        {
            redirectAttributes.addFlashAttribute("message", "Đã xóa 1 bản ghi chú!");
            noteService.delete(ID);
            return "redirect:/manage-note";
        }
        else
            return "redirect:/manage-note";
//            return "redirect:/home/"+ID;
    }
}
