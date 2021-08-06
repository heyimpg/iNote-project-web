package com.amela.Repository;

import com.amela.Models.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INoteRepository extends  IGeneralRepository<Note>{
    List<Note> findByType(int typeID);
    List<Note> findByTitle(String title_name);

    Page<Note> findByType(int typeID, Pageable pageable);
    Page<Note> findByTitle(String title_name, Pageable pageable);
}
