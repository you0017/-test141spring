package com.yc.bean;

import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Data
@ManagedResource(objectName = "com.yc:name=OpRecord")
@Component
public class OpRecord {
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private String datetime;
    private OpType optype;
    private Integer transferid;
}
