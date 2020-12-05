package com.luna.saltfish.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author luna@mac
 */
public class DatabaseConnection {
    private static final String DRIVER     = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL      =
        "jdbc:mysql://localhost:3306/salt-fish?serverTimezone=GMT%2B8&useSSL=false";
    private static final String DBUSER     = "root";
    private static final String DBPASSWORD = "czy1024";
    private Connection          conn;

    public DatabaseConnection() throws Exception {
        Class.forName(DRIVER);
        this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        // this.conn.createStatement().execute("SET NAMES utf8");
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
