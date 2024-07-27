package com.yc;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger log = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
        log.info("Hello World");
    }

    public int test(){
        return 1+1;
    }
}

