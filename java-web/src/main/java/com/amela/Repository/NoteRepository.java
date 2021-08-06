package com.amela.Repository;

import com.amela.Models.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class NoteRepository implements INoteRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<Note> findAll(Pageable pageable) {
        TypedQuery<Note> query = entityManager.createQuery("SELECT c FROM Note c", Note.class);
        List<Note> noteList = query.getResultList();

         int start = (int)pageable.getOffset();
         int end = Math.min((start + pageable.getPageSize()), noteList.size());
         Page<Note> pageList = new PageImpl<>(noteList.subList(start, end), pageable, noteList.size());

        return pageList;
    }

    @Override
    public List<Note> findAll() {
        TypedQuery<Note> query = entityManager.createQuery("SELECT c FROM Note c", Note.class);
        return query.getResultList();
    }

    @Override
    public Note findById(int id) {
        return entityManager.find(Note.class, id);
    }

    @Override
    public void save(Note note) {
        if (note.getID()!=0)
            entityManager.merge(note);
        else
            entityManager.persist(note);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }

    @Override
    public List<Note> findByType(int typeID) {
        TypedQuery<Note> query = entityManager.createQuery("SELECT c FROM Note c WHERE c.type.ID = :typeID", Note.class);
        query.setParameter("typeID", typeID);
        return query.getResultList();
    }
    @Override
    public Page<Note> findByType(int typeID, Pageable pageable) {
        TypedQuery<Note> query = entityManager.createQuery("SELECT c FROM Note c WHERE c.type.ID = :typeID", Note.class);
        query.setParameter("typeID", typeID);
        List<Note> noteList = query.getResultList();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noteList.size());
        Page<Note> pageList = new PageImpl<>(noteList.subList(start, end), pageable, noteList.size());
        return pageList;
    }

    @Override
    public List<Note> findByTitle(String title_name) {
        TypedQuery<Note> query = entityManager.createQuery("SELECT c FROM Note c WHERE c.title like :title_name", Note.class);
        query.setParameter("title_name", "%"+title_name+"%");
        return query.getResultList();
    }


    @Override
    public Page<Note> findByTitle(String title_name, Pageable pageable) {
        TypedQuery<Note> query = entityManager.createQuery("SELECT c FROM Note c WHERE c.title like :title_name", Note.class);
        query.setParameter("title_name", "%"+title_name+"%");
        List<Note> noteList = query.getResultList();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noteList.size());
        Page<Note> pageList = new PageImpl<>(noteList.subList(start, end), pageable, noteList.size());
        return pageList;
    }
}
