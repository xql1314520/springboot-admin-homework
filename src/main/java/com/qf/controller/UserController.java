package com.qf.controller;

import com.qf.pojo.BaseResult;
import com.qf.pojo.TbUser;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping("/validaUserName")
    public BaseResult validaUserName(@RequestBody Map map){

        return userService.validaUserName((String)map.get( "userName" ));
    }
    @RequestMapping("/validaEmail")
    public BaseResult validaEmail(@RequestBody Map map){
        return userService.validaEmail((String)map.get( "email" ));
    }

//    @RequestMapping("/sendMail")
//    public BaseResult sendMail(@RequestBody Map map){
//        return userService.sendMail(map.get( "email" ));
//    }
    @RequestMapping("/registry")
    public BaseResult registry(@RequestBody TbUser tbUser){
        return userService.registry(tbUser);
    }
    @RequestMapping("/login")
    public BaseResult login(@RequestBody TbUser tbUser){
        return userService.login(tbUser);
    }
}

