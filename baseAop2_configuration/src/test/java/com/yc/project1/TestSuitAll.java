package com.yc.project1;

import com.yc.project1.dao.UserDaoImplTest;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;

//测试套件
@RunWith(Suite.class)
@Suite.SuiteClasses({UserDaoImplTest.class, AppConfigTest.class})
public class TestSuitAll {
}
