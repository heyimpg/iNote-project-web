package com.amela.Repository;

import com.amela.Models.Login;
import com.amela.Models.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class LoginRepository implements ILoginRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<Login> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Login> findAll() {
        TypedQuery<Login> query = entityManager.createQuery("SELECT c FROM Login c", Login.class);
        return query.getResultList();
    }

    @Override
    public Login findById(int id) {
        return null;
    }

    @Override
    public void save(Login login) {
        if (login.getID() != 0)
            entityManager.merge(login);
        else
            entityManager.persist(login);
    }

    @Override
    public void delete(int id) {
        System.out.println("Không cho phép xóa account có ID "+id);
    }

    @Override
    public Login findByAccount(String email_search) {
        TypedQuery<Login> query = entityManager.createQuery("SELECT c FROM Login c WHERE c.email =:email_search", Login.class);
        query.setParameter("email_search", email_search);
        try
        {
            return query.getSingleResult();
        }catch (NoResultException e)
        {
            return null;
        }

    }
}
