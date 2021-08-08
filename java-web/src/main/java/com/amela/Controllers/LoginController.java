package com.amela.Controllers;

import com.amela.Models.Login;
import com.amela.Models.Login_Clone;
import com.amela.Service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
//@SessionAttributes("login")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @ModelAttribute("login")
    public Login initLogin()
    {
        return new Login();
    }

//Login
    @GetMapping("/login")
    public String index()
    {
        return "login/index";
    }
    @PostMapping("/login")
    public ModelAndView indexAccept(@ModelAttribute("login") Login login, HttpServletRequest request)
    {
        //Check account
        ModelAndView modelAndView = new ModelAndView("login/index");
        login.toString();
        List<Login> loginList = loginService.findAll();
        Login find_account = loginList.stream().filter(p->p.getEmail().equals(login.getEmail())).findFirst().orElse(null);
        if ( find_account == null)
        {
            modelAndView.addObject("invalid_account", "Ten tai khoan khong ton tai!");
            return modelAndView;
        }
        if (!find_account.getPassword().equals(login.getPassword()))
        {
            modelAndView.addObject("invalid_account", "Sai, vui long kiem tra lai mat khau!");
            return modelAndView;
        }
        //Setup session
        HttpSession session = request.getSession();
        session.setAttribute("login", find_account);

        modelAndView.setViewName("redirect:/manage-note");
        return modelAndView;
    }

//Register
    @GetMapping("/register")
    public String regisAcc()
    {
        return "login/register";
    }
    @PostMapping("/register")
    public ModelAndView regisAccept(@Valid @ModelAttribute("login") Login login, BindingResult bindingResult,
                                    @RequestParam("password_confirm") String password_confirm,
                                    RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView("login/register");
        if (bindingResult.hasFieldErrors())
            return modelAndView;
        if (login.getPassword().equals(password_confirm))
        {
            loginService.save(login);
            redirectAttributes.addFlashAttribute("message", "Dang ky thanh cong tai khoan "+ login.getEmail());
            modelAndView.setViewName("redirect:/login");
        }
        else
            modelAndView.addObject("invalid_password", "Password nhap lai khong khop");
        return modelAndView;
    }

//Logout
    @GetMapping("/logout")
    public String logoutAcc(HttpSession session, HttpServletRequest request) {
        session = request.getSession(false);
        if(session.getAttribute("login")!=null)
        {
            Login login = (Login) session.getAttribute("login");
            if(login.getEmail()!=null)
                System.out.println(login.getName());
            session.invalidate();
        }
        else
        {
            //Chuyển hướng tới trang login
            System.out.println("Chưa có data về session login");
            return "redirect:/not-found-session";
        }
        return "redirect:/home";
    }

//Change password
    @GetMapping("/change-password")
    public ModelAndView changePw(@SessionAttribute("login")Login login) throws Exception
    {
        Login_Clone login_clone = convertLoginToClone(login);
        return new ModelAndView("login/change-password", "login_clone", login_clone);
    }

    @PostMapping("/change-password")
    public ModelAndView changePwAccept(@Valid @ModelAttribute("login_clone") Login_Clone login_clone, BindingResult bindingResult,
             @RequestParam("password_confirm") String password_confirm, HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView("login/change-password");
        if (bindingResult.hasFieldErrors())
            return modelAndView;

        if (login_clone.getPassword().equals(password_confirm))
        {
            Login login = convertCloneToLogin(login_clone);
            loginService.save(login);
            modelAndView.addObject("message", "Da thay doi thong tin cho tai khoan "+ login.getEmail());

            //Re-Setup session
            HttpSession session = request.getSession(false);
            session.setAttribute("login", login);
        }
        else
            modelAndView.addObject("invalid_password", "Password nhap lai khong khop");
        return modelAndView;
    }

//Convert
    public Login_Clone convertLoginToClone(Login login)
    {
        Login_Clone login_clone = new Login_Clone();
        login_clone.setID(login.getID());
        login_clone.setEmail(login.getEmail());
        login_clone.setName(login.getName());
        login_clone.setPassword(login.getPassword());

        return login_clone;
    }
    public Login convertCloneToLogin(Login_Clone login_clone)
    {
        Login login = new Login();
        login.setID(login_clone.getID());
        login.setEmail(login_clone.getEmail());
        login.setName(login_clone.getName());
        login.setPassword(login_clone.getPassword());

        return login;
    }

}
