package com.chen.securityadmin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Target;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.controller
* @className com.chen.securityadmin.controller.LoginController

* @author chenyingtao
* @date 2025/4/10 16:46
* @version 1.0
* @description @todo 
*/
@RequestMapping("/")
@RestController
@Tag(name = "登录接口 test", description = "登录接口")
public class LoginController {

    @RequestMapping("/login-page")
    public String loginPage() {

        return "登录页面";
    }
    @RequestMapping("/login-success")
    public String loginSuccess(){

        return "登录成功";
    }

    @RequestMapping("/login-fail")
    public String loginFail(){
        return "登录失败";
    }


    @RequestMapping("/user/{id}")
    public User getuser(@PathVariable("id") String id){
        return new User("admin","admin",null);
    }

    @RequestMapping("/r/r_admin")
    public String r1(){
        return "访问r1资源";
    }

    @RequestMapping("/r/r_user")
    public String r2(){
        return "访问r_user资源";
    }

    @RequestMapping("/r/r_guess")
    public String r3(){
        return "访问r_guess资源";
    }
}
