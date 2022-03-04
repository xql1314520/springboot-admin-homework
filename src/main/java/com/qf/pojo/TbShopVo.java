package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data

public class TbShopVo {
    private Integer id;
    private String shopName;
    private String creatTime;
    private String pic;
    private Double price;
    private Integer num;
}
