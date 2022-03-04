package com.qf.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @Value( "${jwt.securt}" )
    private String securt;
    @Value( "${jwt.issuer}" )
    private String issuer;

    public String createToken(Map map){
        long l = System.currentTimeMillis();
        l+=1*60*60*1000;
        Date date = new Date(l);

        Algorithm algorithm=Algorithm.HMAC256 ( securt );
        Map headMap=new HashMap<> ();
        headMap.put ( "alg","hs256" );
        headMap.put ( "typ","jwt" );

        String sign= JWT.create ( ).withHeader ( headMap )
                 .withSubject("用户认证")
                .withIssuer(issuer)
                //设置自定义内容用户名称
                .withClaim("username", map.get("username").toString())
                //设置自定义内容用户的id
                .withClaim("id", Integer.valueOf(map.get("id").toString()))
                .withIssuedAt(new Date())
                //设置失效时间
                .withExpiresAt(date)
                .sign(algorithm);

        return sign;
    }
    public Map varferToken(String token){
        Algorithm algorithm=Algorithm.HMAC256 ( securt );
        JWTVerifier bulid=JWT.require ( algorithm ).withIssuer (issuer ).build ();
        try{
            DecodedJWT verify=bulid.verify ( token );
            Map map=new HashMap<>();
            map.put ( "username",verify.getClaim ( "username" ).asString () );
            map.put ( "id",verify.getClaim ( "id" ).asInt () );
            return map;
        }catch(Exception e){
            return null;
        }
    }
}
