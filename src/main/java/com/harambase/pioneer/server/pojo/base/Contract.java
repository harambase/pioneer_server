package com.harambase.pioneer.server.pojo.base;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "serial_num")
    private String serialNum;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "info")
    private String info;

    @Column(name = "init_date")
    private String initDate;

    @Column(name = "expire_date")
    private String expireDate;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "status")
    private String status;

    @Column(name = "operator_id")
    private String operatorId;

    @Column(name = "contract_info")
    private String contractInfo;



}