package com.amela.Service;

import com.amela.Models.NoteType;
import com.amela.Repository.INoteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteTypeService implements INoteTypeService{
    @Autowired
    private INoteTypeRepository noteTypeRepository;

    @Override
    public Page<NoteType> findAll(Pageable pageable) {
        return noteTypeRepository.findAll(pageable);
    }

    @Override
    public List<NoteType> findAll() {
        return noteTypeRepository.findAll();
    }

    @Override
    public NoteType findById(int id) {
        return noteTypeRepository.findById(id);
    }

    @Override
    public void save(NoteType note_type) {
        noteTypeRepository.save(note_type);
    }

    @Override
    public void delete(int id) {
        noteTypeRepository.delete(id);
    }

    @Override
    public Page<NoteType> findByName(String name, Pageable pageable) {
        return noteTypeRepository.findByName(name, pageable);
    }
}
