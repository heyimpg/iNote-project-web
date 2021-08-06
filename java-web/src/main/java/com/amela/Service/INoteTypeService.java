package com.amela.Service;

import com.amela.Models.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INoteTypeService extends IGeneralService<NoteType>{
    Page<NoteType> findByName(String name, Pageable pageable);
}
