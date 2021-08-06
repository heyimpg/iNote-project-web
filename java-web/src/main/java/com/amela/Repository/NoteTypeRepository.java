package com.amela.Repository;

import com.amela.Models.Note;
import com.amela.Models.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class NoteTypeRepository implements INoteTypeRepository{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Page<NoteType> findAll(Pageable pageable) {
        TypedQuery<NoteType> query = entityManager.createQuery("SELECT c FROM NoteType c", NoteType.class);
        List<NoteType> noteTypeList = query.getResultList();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noteTypeList.size());
        Page<NoteType> pageList = new PageImpl<>(noteTypeList.subList(start, end), pageable, noteTypeList.size());

        return pageList;
    }

    @Override
    public List<NoteType> findAll() {
        TypedQuery<NoteType> query = entityManager.createQuery("SELECT c FROM NoteType c", NoteType.class);
        return query.getResultList();
    }

    @Override
    public NoteType findById(int id) {
        return entityManager.find(NoteType.class, id);
    }

    @Override
    public void save(NoteType note_type) {
        if (note_type.getID()!=0)
            entityManager.merge(note_type);
        else
            entityManager.persist(note_type);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }

    @Override
    public Page<NoteType> findByName(String name, Pageable pageable) {
        TypedQuery<NoteType> query = entityManager.createQuery("SELECT c FROM NoteType c WHERE c.name like :name", NoteType.class);
        query.setParameter("name", "%"+name+"%");
        List<NoteType> noteTypeList = query.getResultList();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noteTypeList.size());
        Page<NoteType> pageList = new PageImpl<>(noteTypeList.subList(start, end), pageable, noteTypeList.size());
        return pageList;
    }
}
