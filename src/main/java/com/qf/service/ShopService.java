package com.qf.service;

import com.qf.pojo.BaseResult;
import com.qf.pojo.TbShop;

public interface ShopService {
    BaseResult findAll(Integer page ,Integer size);

    BaseResult findById(Integer id);

    BaseResult updateOrInsert(TbShop shop);

    BaseResult del(Integer id);
}
