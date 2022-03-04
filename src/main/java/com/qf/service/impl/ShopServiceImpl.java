package com.qf.service.impl;

import com.qf.dao.ShopRepository;
import com.qf.pojo.BaseResult;
import com.qf.pojo.TbShop;
import com.qf.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public BaseResult findAll(Integer page, Integer size) {

        Boolean shop = redisTemplate.hasKey ( "shop" );
        if(shop){
            System.out.println ("进入了redis查询" );
            int star=(page-1)*size;
            List shop1 = redisTemplate.opsForList ( ).range ( "shop" ,star ,star+size-1 );
            Long total = redisTemplate.opsForList ( ).size ( "shop" );
            return new BaseResult (0, "查询成功");

        }else{
            System.out.println ("进入了数据库查询" );
            List<TbShop> all = shopRepository.findAll ( );
            redisTemplate.opsForList ().leftPush ( "shop",all );
            PageRequest of = PageRequest.of(page - 1, size);
            Page<TbShop> shops = shopRepository.findAll(of);

            return new BaseResult(0,"查询成功！",shops.getContent(),shops.getTotalElements());

        }

//        PageRequest of = PageRequest.of(page - 1, size);
//        Page<TbShop> all = shopRepository.findAll(of);
//
//        return new BaseResult(0,"查询成功！",all.getContent(),all.getTotalElements());
    }

    @Override
    public BaseResult findById(Integer id) {
        Optional<TbShop> byId = shopRepository.findById( id );
        return new BaseResult( 0,"查询成功",byId.get() );
    }

    @Override
    public BaseResult updateOrInsert(TbShop shop) {
        TbShop tbShop = shopRepository.saveAndFlush( shop );
        if(tbShop!=null){
            redisTemplate.delete ( "shop" );
            return new BaseResult( 0,"ok" );
        }
        return new BaseResult( 1,"not ok" );
    }

    @Override
    public BaseResult del(Integer id) {

        shopRepository.deleteById( id );

        redisTemplate.delete ( "shop" );
        return new BaseResult( 0,"ok" );
    }
}
