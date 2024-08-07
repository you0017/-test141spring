package com.yc;


// 导入JUnit的Suite注解，用于定义一个测试套件
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// 使用@RunWith注解，指定测试运行器为Suite，这样JUnit会运行所有标记为@Test的方法
@RunWith(Suite.class)
// 使用@Suite.SuiteClasses注解，指定要包含在测试套件中的测试类
@Suite.SuiteClasses({
        AppDataSourceConfigTest.class // 这里列出了要包含的测试类，可以添加更多的类
})

// 定义一个名为TestAll的公共类，作为测试套件的入口点
public class TestAll {

    // 这个类不需要包含任何方法，因为它只是一个容器，用于组织和运行其他测试类
}

