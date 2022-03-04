package com.qf.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.qf.dao.PermissionMapper;
import com.qf.pojo.BaseResult;
import com.qf.pojo.Permission;
import com.qf.pojo.TbUser;
import com.qf.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    JwtUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request ,HttpServletResponse response ,Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        String token="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals( "token" )) {
                    token=cookie.getValue();
                }
            }
        }
        if(token.isEmpty()){
            response.setCharacterEncoding( "utf-8" );
            response.setContentType( "application/json" );
            response.getWriter().write( JSONObject.toJSONString( new BaseResult( 1,"用户未登录 B服务"  ) ) );

            return false;
        }
       // Object o = redisTemplate.opsForValue().get( rediskey );

        Map map=jwtUtils.varferToken(token);
        if(map==null){
            response.setCharacterEncoding( "utf-8" );
            response.setContentType( "application/json" );
            response.getWriter().write( JSONObject.toJSONString( new BaseResult( 1,"用户登录失效，重新登录" ) ) );
            return false;
        }
 //       request.setAttribute ( "userid",Integer.valueOf ( map.get ( "id" ).toString () ) );

//        String string = JSONObject.toJSONString( o );
//        TbUser tbUser = JSONObject.parseObject( (String) o,TbUser.class );
//        List<Permission> byUserName = permissionMapper.findByUserName( tbUser.getUserName() );
        //request.setAttribute( "id",tbUser.getId() );
//        List perList=new ArrayList<>(  );
//
//        for(Permission list:byUserName){
//            perList.add( list.getPermissionName() );
//        }
//        String method1= request.getMethod();
//        String requestURI = request.getRequestURI();
//        String[] split = requestURI.split( "/" );
//
//        if(!perList.contains(split[2])){
//            request.setCharacterEncoding( "utf-8" );
//            response.setContentType( "application/json" );
//            response.getWriter().write( JSONObject.toJSONString( new BaseResult( 1,"用户没权限" ) ) );
//            return false;
//        }


        return true;
        
    }
    
}
