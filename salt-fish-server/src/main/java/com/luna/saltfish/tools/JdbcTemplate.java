package com.luna.saltfish.tools;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * jdbc操作模板
 */
public class JdbcTemplate {

    /**
     * 对应 添加、修改、删除
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static int update(String sql, Object... params) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        return queryRunner.update(DbHelp.getConnection(), sql, params);
    }

    /**
     * 查询单行单列的数据，比如 select count(*) from table
     * 
     * @param sql
     * @param resultSetHandler
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T> T query(String sql, ScalarHandler<T> resultSetHandler, Object... params) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        return queryRunner.query(DbHelp.getConnection(), sql, resultSetHandler, params);
    }

    /**
     * 查询返回单个对象
     * 
     * @param sql
     * @param resultSetHandler
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T> T query(String sql, BeanHandler<T> resultSetHandler, Object... params) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        return queryRunner.query(DbHelp.getConnection(), sql, resultSetHandler, params);
    }

    /**
     * 查询返回集合列表
     * 
     * @param sql
     * @param resultSetHandler
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T> List<T> query(String sql, BeanListHandler<T> resultSetHandler, Object... params)
        throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        return queryRunner.query(DbHelp.getConnection(), sql, resultSetHandler, params);
    }
}
