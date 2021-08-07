package com.amela.Service;

import com.amela.Models.Login;
import com.amela.Repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements ILoginService{
    @Autowired
    private ILoginRepository loginRepository;

    @Override
    public Page<Login> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Login findById(int id) {
        return null;
    }

    @Override
    public void save(Login login) {
        loginRepository.save(login);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Login findByAccount(String email_search) {
        return loginRepository.findByAccount(email_search);
    }
}
