package com.qf.service;

import com.qf.pojo.BaseResult;

public interface ShopCartService {
    BaseResult add(Integer shopid ,String userid);

    BaseResult del(Integer shopid ,String userid);

    BaseResult findAllShopCart(String userid);
}
