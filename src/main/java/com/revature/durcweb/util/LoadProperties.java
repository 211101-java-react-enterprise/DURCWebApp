package com.revature.durcweb.util;

import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    static public Properties loadProp() {
        Properties props = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            props.load(loader.getResourceAsStream("DB.properties"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
