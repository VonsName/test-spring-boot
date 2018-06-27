package com.example.test.testspringboot.controller;

import com.example.test.testspringboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(User user){
        System.out.println(user);
        return "/";
    }
}
