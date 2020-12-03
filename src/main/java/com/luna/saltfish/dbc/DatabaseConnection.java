package com.luna.saltfish.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author luna@mac
 */
public class DatabaseConnection {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://111.229.114.126:3307/salt-fish";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "czy1024";
    private Connection conn;

    public DatabaseConnection() throws Exception {
        Class.forName(DBDRIVER);
        this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        //this.conn.createStatement().execute("SET NAMES utf8");
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
