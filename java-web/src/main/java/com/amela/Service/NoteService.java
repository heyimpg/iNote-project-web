package com.amela.Service;

import com.amela.Models.Note;
import com.amela.Repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements  INoteService {
    @Autowired
    private INoteRepository iNoteRepository;

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return iNoteRepository.findAll(pageable);
    }

    @Override
    public List<Note> findAll() {
        return iNoteRepository.findAll();
    }

    @Override
    public Note findById(int id) {
        return iNoteRepository.findById(id);
    }

    @Override
    public void save(Note note) {
        iNoteRepository.save(note);
    }

    @Override
    public void delete(int id) {
        iNoteRepository.delete(id);
    }

    @Override
    public List<Note> findByType(int typeID) {
        return iNoteRepository.findByType(typeID);
    }
    @Override
    public Page<Note> findByType(int typeID, Pageable pageable) {
        return iNoteRepository.findByType(typeID, pageable);
    }

    @Override
    public List<Note> findByTitle(String title_name) {
        return iNoteRepository.findByTitle(title_name);
    }

    @Override
    public Page<Note> findByTitle(String title_name, Pageable pageable) {
        return iNoteRepository.findByTitle(title_name, pageable);
    }
}
