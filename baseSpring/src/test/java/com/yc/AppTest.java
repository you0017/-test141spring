package com.yc;


import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    public static Logger log = Logger.getLogger(AppTest.class);

    @Test
    public void testApp() {
        log.info("testApp");
        App app = new App();
        Assert.assertEquals(app.test(),2);
    }
}
