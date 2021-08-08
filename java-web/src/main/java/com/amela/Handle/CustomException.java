package com.amela.Handle;

import org.hibernate.SessionException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomException {

    //Handle cho việc k nhập thời gian (đang bị lỗi Exception chặn)
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView exceptionNotFoundSession()
    {
        ModelAndView modelAndView = new ModelAndView("exception_handle/notFoundPage_error");
        return modelAndView;
    }

    //Handle chung cho việc chưa login
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionNotFoundPage()
    {
        //Bắt tạm lỗi session với Exception
        ModelAndView modelAndView = new ModelAndView("exception_handle/notFoundSession_error");
        return modelAndView;
    }

}
