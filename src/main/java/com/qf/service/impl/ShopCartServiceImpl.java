package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.dao.ShopRepository;
import com.qf.pojo.BaseResult;
import com.qf.pojo.TbShop;
import com.qf.pojo.TbShopVo;
import com.qf.service.ShopCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ShopRepository shopRepository;

    @Override
    public BaseResult add(Integer shopid ,String userid) {

//        Boolean aBoolean = redisTemplate.hasKey ( "shopcart_" + userid );
//        redisTemplate.opsForHash ( ).put ( "shopcart_" + userid ,null ,null );
        Object o = redisTemplate.opsForHash ( ).get ( "shopcart_" + userid.toString ( ) ,shopid.toString ( ) );
        if (o != null) {
            String s = JSONObject.toJSONString ( o );
            TbShopVo tbShopVo = JSONObject.parseObject ( s ,TbShopVo.class );
            Integer num = tbShopVo.getNum ( );
            tbShopVo.setNum ( num + 1 );
            redisTemplate.opsForHash ().put ( "shopcart_"+userid,shopid.toString (),tbShopVo );

            return new BaseResult ( 0 ,"ok" );
        } else {
            Optional<TbShop> byId = shopRepository.findById ( shopid );
            TbShop tbShop = byId.get ( );
            TbShopVo tbShopVo = new TbShopVo ( );
            BeanUtils.copyProperties ( tbShop ,tbShopVo );
            tbShopVo.setNum ( 1 );
            redisTemplate.opsForHash ( ).put ( "shopcart_" + userid.toString ( ) ,shopid.toString ( ) ,tbShopVo );
            return new BaseResult ( 0 ,"ok" );
        }

    }

    @Override
    public BaseResult del(Integer shopid ,String userid) {
        Boolean aBoolean = redisTemplate.hasKey ( "shopcart_" + userid );
        if (aBoolean) {
            Object o = redisTemplate.opsForHash ( ).get ( "shopcart_" + userid ,shopid.toString ( ) );
            String s = JSONObject.toJSONString ( o );
            TbShopVo tbShopVo = JSONObject.parseObject ( s ,TbShopVo.class );
            Integer num = tbShopVo.getNum ( );

            if (num > 1) {
                tbShopVo.setNum ( num - 1 );
                redisTemplate.opsForHash ( ).put ( "shopcart_" + userid ,shopid.toString ( ) ,tbShopVo );
                return new BaseResult ( 0 ,"ok" );
            } else {
                redisTemplate.opsForHash ( ).delete ( "shopcart_" + userid ,shopid.toString () );
                return new BaseResult ( 0 ,"ok" );
            }
        } else {
            return new BaseResult ( 1 ,"请添加商品" );
        }
    }

    @Override
    public BaseResult findAllShopCart(String userid) {
        Boolean aBoolean = redisTemplate.hasKey ( "shopcart_" + userid );
        if(aBoolean){
            List values = redisTemplate.opsForHash ( ).values ( "shopcart_" + userid );
            return new BaseResult ( 0,"ok",values );
        }else{
            return new BaseResult ( 1,"请添加商品" );
        }
    }
}
