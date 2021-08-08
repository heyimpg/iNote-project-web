package com.amela.Controllers;

import com.amela.Models.Login;
import com.amela.Models.Note;
import com.amela.Models.NoteType;
import com.amela.Service.INoteService;
import com.amela.Service.INoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteTypeController {
    @Autowired
    private INoteService noteService;
    @Autowired
    private INoteTypeService noteTypeService;

    @ModelAttribute("note_types")
    public List<NoteType> initNoteTypeList()
    {
        return noteTypeService.findAll();
    }

    @ModelAttribute("note_type")
    public NoteType initNoteType()
    {
        return new NoteType();
    }

//List
    @GetMapping("/note-types")
    public ModelAndView index(@RequestParam("searchView") Optional<String> search,
                              @PageableDefault(value = 5) Pageable pageable,
                              @SessionAttribute("login") Login login) throws Exception
    {
        ModelAndView modelAndView = new ModelAndView("note_type/index");
        if (search.isPresent())
        {
            modelAndView.addObject("searchResult", search.get());
            modelAndView.addObject("note_types_page", noteTypeService.findByName(search.get(), pageable) );
        }
        else
            modelAndView.addObject("note_types_page", noteTypeService.findAll(pageable));

        return modelAndView;
    }

//Create
    @GetMapping("/create-type")
    public String create()
    {
        return "note_type/create";
    }
    @PostMapping("/create-type-accept")
    public String createAccept(@Valid @ModelAttribute("note_type") NoteType noteType, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasFieldErrors())
        {
            return "note_type/create";
        }
        else
        {
//            noteTypeService.save(noteType);
            redirectAttributes.addFlashAttribute("message", "Da them moi 1 danh muc thanh cong!");
            return "redirect:/create-type";
        }

    }

//Edit
    @GetMapping("/edit-type/{id}")
    public ModelAndView edit(@PathVariable("id") int ID) {
        ModelAndView modelAndView = new ModelAndView("note_type/edit");
        NoteType noteType = noteTypeService.findById(ID);
        modelAndView.addObject("note_type_edit", noteType);
        return modelAndView;
    }
    @PostMapping("/edit-type-accept")
    public String editAccept(@Valid @ModelAttribute("note_type_edit") NoteType noteType, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
            if (bindingResult.hasFieldErrors())
            {
                return "note_type/edit";
            }
            else
            {
                noteTypeService.save(noteType);
                redirectAttributes.addFlashAttribute("message", "Luu thanh cong!");
                return "redirect:/edit-type/"+noteType.getID();
            }
    }
}
