package com.amela.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/update-err")
    public String updateErr()
    {
        return "update_error";
    }
}
