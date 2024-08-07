package com.yc.bean;

import lombok.Data;

import java.util.Date;

@Data
public class OpRecord {
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private String datetime;
    private OpType optype;
    private Integer transferid;
}
