package com.yc.ioc.bean6_conditional;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SystemConditional implements Condition {
    private static Logger log = Logger.getLogger(SystemConditional.class);
    @Override
    //                     context代表当前condition注解所在的上下文
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        String property = env.getProperty("user.home");
        String property1 = env.getProperty("user.dir");
        String property2 = env.getProperty("os.name");
        String property3 = env.getProperty("JAVAHOME");
        /*log.info("user.home:"+property);
        log.info("user.dir:"+property1);
        log.info("os.name:"+property2);
        log.info("JAVAHOME:"+property3);*/
        if ("Windows 10".equals(property2)){
            return true;
        }
        return false;
    }
}
