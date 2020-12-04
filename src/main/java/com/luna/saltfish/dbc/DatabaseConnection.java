package com.luna.saltfish.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author luna@mac
 */
public class DatabaseConnection {
    private static final String driver     = "com.mysql.cj.jdbc.Driver";
    private static final String dburl      =
        "jdbc:mysql://111.229.114.126:3307/salt-fish?serverTimezone=GMT%2B8&useSSL=false";
    private static final String dbuser     = "root";
    private static final String dbpassword = "czy1024";
    private Connection          conn;

    public DatabaseConnection() throws Exception {
        Class.forName(driver);
        this.conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
        // this.conn.createStatement().execute("SET NAMES utf8");
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() throws Exception {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (Exception e) {
                throw e;
            }
        }
    }
}
