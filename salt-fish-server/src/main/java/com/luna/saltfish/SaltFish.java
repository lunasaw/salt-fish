package com.luna.saltfish;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * @className SaltFish.java
 * @author luna@mac
 * @description TODO
 * @createTime 2020年12月04日 09:20:00
 */
public class SaltFish {

    private static final int    DEFAULT_PORT = 80;

    private static final String DOC_BASE     = "src/main/webapp/";

    private static final String CONTEXT_PATH = "/salt-fish";

    /**
     * tomcat加入web工程
     *
     * host:缺省默认为localhost
     * contextPath:在浏览器中访问项目的根路径
     * 例：localhost:port/{contextPath}/xx
     * docBase：项目中webapp所在路径
     *
     */
    public static void start() {
        Tomcat tomcat = new Tomcat();
        tomcat.setSilent(false);
        try {
            tomcat.addWebapp(CONTEXT_PATH, new File(DOC_BASE).getAbsolutePath());
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SaltFish.start();
    }

}
