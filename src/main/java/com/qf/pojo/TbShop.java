package com.qf.pojo;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Data
@Entity
@Table(name="shop")
public class TbShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="shop_name")
    private String shopName;

    @Column(name = "create_time")
    private String creatTime;

    private String pic;

    private Double price;
}
