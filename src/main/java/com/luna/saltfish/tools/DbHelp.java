package com.luna.saltfish.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库工具类
 */
public class DbHelp {
    /**
     * 数据源
     */
    private static DataSource              dataSource  = null;
    /**
     * 本地线程对象，绑定数据库连接对象
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            // 创建c3p0数据源
            dataSource = new ComboPooledDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据连接对象
     *
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 从当前线程中获取连接对象
        Connection connection = threadLocal.get();
        if (connection == null) {
            // 如果当前线程中灭有连接对象，就从数据源中获取一个连接对象
            connection = dataSource.getConnection();
            // 将获取的连接对象存入当前线程
            threadLocal.set(connection);
        }
        // 返回连接对象
        return connection;
    }

    /**
     * 设置事务的提交方式
     *
     * @param auto false表示手动提交，true表示自动提交
     * @throws SQLException
     */
    public static void setAutoCommit(boolean auto) throws SQLException {
        getConnection().setAutoCommit(auto);
    }

    /**
     * 设置手动提交
     *
     * @throws SQLException
     */
    public static void setAutoCommit() throws SQLException {
        setAutoCommit(false);
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        getConnection().commit();
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        getConnection().rollback();
    }

    /**
     * 关闭资源通用方法
     *
     * @throws SQLException
     */
    private static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        // 连接对象不为空关闭
        if (connection != null) {
            // 关闭连接
            getConnection().close();
            // 从线程中移除
            threadLocal.remove();
            // 变量置为空，等待垃圾回收器回收
            connection = null;
        }
        // 执行器不为空关闭
        if (statement != null) {
            // 关闭执行器
            statement.close();
            // 变量置为空，等待垃圾回收器回收
            statement = null;
        }
        // 结果集不为空
        if (resultSet != null) {
            // 关闭结果集
            resultSet.close();
            // 变量置为空，等待垃圾回收器回收
            resultSet = null;
        }
    }

    /**
     * 关闭资源
     *
     * @param statement 执行器对象
     * @param resultSet 结果集对象
     * @throws SQLException
     */
    public static void close(Statement statement, ResultSet resultSet) throws SQLException {
        close(null, statement, resultSet);
    }

    /**
     * 关闭连接
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        close(getConnection(), null, null);
    }

}
