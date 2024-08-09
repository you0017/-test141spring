package com.yc.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
    private Integer accountid;
    private Double balance;
}
