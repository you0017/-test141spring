package com.yc.bean2.son;

import org.springframework.stereotype.Repository;

//@Repository=@Component+语义:表示这是一个持久层的类,这个类由spring管理,相当于<bean id="productDao" class="com.yc.bean2.son.ProductDao"/>
@Repository //这个注解不同之处:会把抛出的异常转为RuntimeException
public class ProductDao {
}
