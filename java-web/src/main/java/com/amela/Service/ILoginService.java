package com.amela.Service;

import com.amela.Models.Login;

public interface ILoginService extends IGeneralService<Login>{
    Login findByAccount(String email_search);
}
