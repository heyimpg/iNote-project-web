package com.amela.Controllers.CustomeAnnotation;

import com.amela.Models.Login;
import com.amela.Service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

public class UniqueValidator implements ConstraintValidator<UniqueAccount, String> {
    @Autowired
    private ILoginService loginService;

    @Override
    public void initialize(UniqueAccount constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) throws ValidationException {
        if (loginService == null)
            return true;
        return loginService.findByAccount(value)==null;
    }
}
