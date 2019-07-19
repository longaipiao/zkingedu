package com.zking.zkingedu.common.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MyError implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        /*1、按错误的类型显示错误的网页*/
        /*错误类型为404，找不到网页的，默认显示404.html网页*/
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
        /*错误类型为500，表示服务器响应错误，默认显示500.html网页*/
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
        /*2、按具体某个异常显示错误的网页*/
        /*当某个异常即可以根据错误类型显示错误网页，由可以根据某个具体的异常来显示错误网页时，优先根据具体的某个异常显示错误的网页*/
//        ErrorPage argsException = new ErrorPage(IllegalArgumentException.class, "/args.html");
        registry.addErrorPages(e404, e500);
    }

}
