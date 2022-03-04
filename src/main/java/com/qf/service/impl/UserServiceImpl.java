package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.dao.UserRepository;
import com.qf.pojo.BaseResult;
import com.qf.pojo.TbUser;
import com.qf.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisTemplate redisTemplate;

//    @Value( "${spring.mail.username}" )
//    private String from;


    @Override
    public BaseResult validaUserName(String userName) {
        TbUser byUserName=userRepository.findByUserName(userName);
        if(byUserName!=null){
            return new BaseResult( 1,"用户名重复" );

        }
        return new BaseResult( 0,"用户名没有被占用" );

    }

    @Override
    public BaseResult validaEmail(String email) {
        TbUser byUserName=userRepository.findByEmail( email );
        if(byUserName!=null){
            return new BaseResult( 1,"邮箱被占用" );

        }
        return new BaseResult( 0,"邮箱没有被占用" );
    }

//    @Override
//    public BaseResult sendMail(Object email) {
//        TbUser byUserName=userRepository.findByEmail((String) email );
//        if(byUserName!=null){
//            return new BaseResult( 1,"邮箱被占用" );
//
//        }
//        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//        simpleMailMessage.setFrom( from );
//        simpleMailMessage.setTo((String)email );
//        simpleMailMessage.setSubject( "前锋验证码" );
//        Random random=new Random();
//        StringBuffer stringBuffer=new StringBuffer();
//        for(int i=0;i<4;i++){
//            stringBuffer.append( random.nextInt(10) );
//
//        }
//        simpleMailMessage.setText(stringBuffer.toString());
//        javaMailSender.send( simpleMailMessage );
//        redisTemplate.opsForValue().set( (String) email,stringBuffer.toString() );
//        return new BaseResult( 0,"发送成功" );
//    }

    @Override
    public BaseResult registry(TbUser tbUser) {
//        String o=(String) redisTemplate.opsForValue().get( tbUser.getEmail() );
//        if(o!=null&&o.equals( tbUser.getEmail() )){
//            return new BaseResult( 1,"验证码不一致" );
//        }

        TbUser save = userRepository.save( tbUser );
       if(save==null){
           return new BaseResult( 1,"注册失败" );
       }
        return new BaseResult( 0,"注册成功" );
    }

    @Override
    public BaseResult login(TbUser tbUser) {
        TbUser byUserName = userRepository.findByUserName( tbUser.getUserName() );

        if(byUserName==null){
            return new BaseResult( 1,"没有该用户,请先注册" );
        }
        if(!byUserName.getPassword().equals( tbUser.getPassword() )){
            return new BaseResult( 1,"密码输入错误" );
        }

        UUID uuid = UUID.randomUUID();
        String string = JSONObject.toJSONString( byUserName );
        redisTemplate.opsForValue().set( uuid.toString(),string );
        return new BaseResult( 0,"登陆成功",uuid .toString());


    }
}
