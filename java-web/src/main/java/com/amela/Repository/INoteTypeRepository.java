package com.amela.Repository;

import com.amela.Models.Note;
import com.amela.Models.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INoteTypeRepository extends IGeneralRepository<NoteType> {
    Page<NoteType> findByName(String name, Pageable pageable);
}
