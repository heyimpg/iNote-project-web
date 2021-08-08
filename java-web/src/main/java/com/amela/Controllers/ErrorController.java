package com.amela.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/update-err")
    public String updateErr()
    {
        return "exception_handle/update_error";
    }
    @GetMapping("/not-found-session")
    public String notFoundSession()
    {
        return "exception_handle/notFoundSession_error";
    }
}
