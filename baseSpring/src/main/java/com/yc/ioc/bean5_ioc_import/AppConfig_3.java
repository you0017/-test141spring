package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean2.son.Pear;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {Pear.class, FruitImportSelector.class, FruitNameImportBeanDefinitionRegistrar.class})
public class AppConfig_3 {
}
