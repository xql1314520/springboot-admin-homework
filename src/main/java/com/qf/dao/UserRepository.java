package com.qf.dao;

import com.qf.pojo.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TbUser,Integer> {


    TbUser findByEmail(String email);

    TbUser findByUserName(String userName);
}
