package com.yc.ioc.bean6_conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(value = {SystemConditional.class})
public class WindowsPath {
}
