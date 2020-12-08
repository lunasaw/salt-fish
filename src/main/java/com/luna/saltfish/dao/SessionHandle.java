package com.luna.saltfish.dao;

import com.luna.saltfish.tools.JdbcTemplate;
import com.luna.saltfish.vo.Session;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @author luna@mac
 * @className SessionHandle.java
 * @description TODO
 * @createTime 2020年12月04日 10:27:00
 */
public class SessionHandle {
    /**
     * 新增session
     *
     * @param session
     * @return
     * @throws SQLException
     */
    public boolean insert(Session session) throws SQLException {
        String sql = "INSERT INTO sessionkey (session_key, user_id) VALUES (?,?);";
        return JdbcTemplate.update(sql, session.getKey(), session.getId()) > 0;
    }

    /**
     * 得到Session Key
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    public String getSession(int userId) throws SQLException {
        String sql = "select session_key from sessionkey where user_id =?";
        return JdbcTemplate.query(sql, new ScalarHandler<String>(), userId);
    }
}
