package com.amela.Controllers;

import com.amela.Models.Login;
import com.amela.Service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @ModelAttribute("login")
    public Login initLogin()
    {
        return new Login();
    }

    @GetMapping("/login")
    public String index()
    {
        return "login/index";
    }
    @PostMapping("/login")
    public ModelAndView indexAccept(@ModelAttribute("login") Login login)
    {
        ModelAndView modelAndView = new ModelAndView("login/index");
        login.toString();
        List<Login> loginList = loginService.findAll();
        Login find_account = loginList.stream().filter(p->p.getEmail().equals(login.getEmail())).findFirst().orElse(null);
        if ( find_account == null)
        {
            modelAndView.addObject("invalid_account", "Tên tài khoản không tồn tại !");
            return modelAndView;
        }
        if (!find_account.getPassword().equals(login.getPassword()))
        {
            modelAndView.addObject("invalid_account", "Sai, vui lòng kiếm tra lại mật khẩu !");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/manage-note");
        return modelAndView;
    }

    @GetMapping("/register")
    public String regisAcc()
    {
        return "login/register";
    }
    @PostMapping("/register")
    public ModelAndView regisAccept(@Valid @ModelAttribute("login") Login login, BindingResult bindingResult, @RequestParam("password_confirm") String password_confirm, RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView("login/register");
        if (bindingResult.hasFieldErrors())
            return modelAndView;
        if (login.getPassword().equals(password_confirm))
        {
            loginService.save(login);
            redirectAttributes.addFlashAttribute("message", "Đăng ký thành công tài khoản "+ login.getEmail());
            modelAndView.setViewName("redirect:/login");
        }
        else
            modelAndView.addObject("invalid_password", "Mật khẩu nhập lại không khớp");

        return modelAndView;
    }




}
