package com.qf.service;

import com.qf.pojo.BaseResult;
import com.qf.pojo.TbUser;

public interface UserService {

    BaseResult validaUserName(String userName);

    BaseResult validaEmail(String email);

//    BaseResult sendMail(Object email);

    BaseResult registry(TbUser tbUser);

    BaseResult login(TbUser tbUser);
}
