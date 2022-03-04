package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.dao.TbOrderRepository;
import com.qf.pojo.TbOrder;
import com.qf.pojo.TbShopVo;
import com.qf.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AlipayServiceImpl implements AlipayService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    TbOrderRepository tbOrderRepository;

    @Override
    public String pay(List ids,String userid) {
        Boolean aBoolean = redisTemplate.hasKey ( "shopcart_" + userid );
        Double price=0.0;
        String uuid = UUID.randomUUID ( ).toString ();

        if(aBoolean){
            for(int i=0;i<=ids.size();i++){
                Object o = redisTemplate.opsForHash ( ).get ( "shopcart_" + userid ,ids.get ( i ).toString ( ) );
                String string = JSONObject.toJSONString (o );
                TbShopVo tbShopVo = JSONObject.parseObject ( string ,TbShopVo.class );
                price+=tbShopVo.getPrice ()*tbShopVo.getNum ();

            }
            TbOrder tbOrder = new TbOrder ( );
            tbOrder.setCreateTime ( new Date());
            tbOrder.setOrderid ( uuid );
            tbOrder.setPrice ( price );
            tbOrder.setStatus ("WATI_PAY");

            this.tbOrderRepository.save ( tbOrder );
        }
        return "ok";
    }
}
