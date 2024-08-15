package com.yc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Integer id;
    private String name;

    //只写属性，返回的json不会包含这个
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer age;
}
