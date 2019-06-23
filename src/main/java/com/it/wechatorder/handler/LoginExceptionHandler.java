package com.it.wechatorder.handler;

import com.it.wechatorder.config.ProjectUrlConfig;
import com.it.wechatorder.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class LoginExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                                .concat(projectUrlConfig.getSell())
                                .concat("/sell//seller/login/login"));
    }
}
