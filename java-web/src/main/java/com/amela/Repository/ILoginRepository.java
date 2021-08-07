package com.amela.Repository;

import com.amela.Models.Login;

public interface ILoginRepository extends IGeneralRepository<Login>{
    Login findByAccount(String email_search);
}
