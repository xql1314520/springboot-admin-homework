package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="tb_order")
@Entity
public class TbOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String orderid;

    private  Double price;

    private String status;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="pay_time")
    private Date payTime;

}
