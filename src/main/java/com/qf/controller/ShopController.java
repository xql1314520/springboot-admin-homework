package com.qf.controller;

import com.qf.pojo.BaseResult;
import com.qf.pojo.TbShop;
import com.qf.pojo.TbUser;
import com.qf.service.ShopService;
import com.qf.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;
    @RequestMapping("/findAll/{page}/{size}")
    public BaseResult findAll(@PathVariable("page")Integer page,@PathVariable("size")Integer  size){
        System.out.println("findAll:"+shopService.findAll( page,size ));
        return shopService.findAll(page,size);
    }
    @RequestMapping("/findById")
    public BaseResult findById(@RequestBody Map map){
        return shopService.findById(Integer.valueOf( map.get( "id" ).toString() ));


    }
    @RequestMapping("/updateOrInsert")
    public BaseResult updateOrInsert(@RequestBody TbShop shop){
        return shopService.updateOrInsert(shop);

    }
    @RequestMapping("/del")
    public BaseResult del(@RequestBody Map map){
        return shopService.del(Integer.valueOf( map.get( "id" ).toString() ));
    }
    @RequestMapping("/upload")
    public String upload(MultipartFile file){
        UploadUtils uploadUtils = new UploadUtils();
        return uploadUtils.upload( file );

    }
}
