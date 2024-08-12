package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageBean {
    private Account account;
    private double money;
    private Integer toAccountid;
    private String email;
    private String opType;
}
