package com.qf.dao;

import com.qf.pojo.BaseResult;
import com.qf.pojo.TbShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<TbShop,Integer> {

}
