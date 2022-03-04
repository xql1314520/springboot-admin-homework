package com.qf.dao;


import com.qf.pojo.TbOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbOrderRepository extends JpaRepository<TbOrder,Integer> {
}
