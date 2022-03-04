package com.qf.controller;

import com.qf.pojo.BaseResult;
import com.qf.service.ShopCartService;
import com.qf.utils.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/shopcart")
public class ShopCartController {

    @Autowired
    ShopCartService shopCartService;
    @Autowired
    JwtUtils jwtUtils;
    @RequestMapping("/add/{id}")
    public BaseResult add(@PathVariable("id")Integer shopid,HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        String token="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals( "token" )) {
                    token=cookie.getValue();
                }
            }
        }
        Map map=jwtUtils.varferToken(token);
        String userid=map.get( "id" ).toString ();
        return shopCartService.add(shopid,userid);
    }
    @RequestMapping("/findAllShopCart")
    public BaseResult findShopCart(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals( "token" )) {
                    token=cookie.getValue();
                }
            }
        }
        Map map=jwtUtils.varferToken(token);
        String userid=map.get( "id" ).toString ();
        return shopCartService.findAllShopCart(userid);
    }

    @RequestMapping("/del/{id}")
    public BaseResult del(@PathVariable("id")Integer shopid,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals( "token" )) {
                    token=cookie.getValue();
                }
            }
        }
        Map map=jwtUtils.varferToken(token);
        String userid=map.get( "id" ).toString ();

        return shopCartService.del(shopid,userid);
    }
}
