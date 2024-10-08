package com.yc.di.bean1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@PropertySource("classpath:product.properties")
public class Product {
    @Value("${pid}")
    private int id;
    @Value("${pname}")
    private String pname;
}
