package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Grape;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 快速导入bean
 */
public class FruitImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //读取一个jar下所有的配置文件 properties
        //这个文件的结构: 接口1路径=实现类1.class,实现类2.class

        return new String[]{Grape.class.getName(), "com.yc.bean5_ioc_import.other.WaterMelon"};
    }
}
