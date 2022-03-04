package com.qf.controller;

import com.qf.service.AlipayService;
import com.qf.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ali")
public class AlipayController {


    @Autowired
    AlipayService alipayService;
    @Autowired
    JwtUtils jwtUtils;
    @RequestMapping("/pay")

    public String pay(@RequestBody Map map,HttpServletRequest request){
        List ids = (List)map.get ( "ids" );
        Cookie[] cookies = request.getCookies();
        String token="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals( "token" )) {
                    token=cookie.getValue();
                }
            }
        }
        Map maps=jwtUtils.varferToken(token);
        String userid=map.get( "id" ).toString ();
        System.out.println (ids );
        return alipayService.pay(ids,userid);

    }
}
