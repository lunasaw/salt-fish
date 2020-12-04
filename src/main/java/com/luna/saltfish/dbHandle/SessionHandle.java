package com.luna.saltfish.dbHandle;

import com.luna.saltfish.dbc.DatabaseConnection;
import com.luna.saltfish.tools.MD5;
import com.luna.saltfish.vo.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author luna@mac
 * @className SessionHandle.java
 * @description TODO
 * @createTime 2020年12月04日 10:27:00
 */
public class SessionHandle {

    private Connection        conn  = null;
    private PreparedStatement pstmt = null;

    public SessionHandle() {
        try {
            this.conn = new DatabaseConnection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增session
     *
     * @param session
     * @return
     * @throws SQLException
     */
    public boolean insert(Session session) throws SQLException {
        try {
            String sql = "INSERT INTO sessionkey (session_key, user_id) VALUES (?,?);";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, session.getKey());
            this.pstmt.setInt(2, session.getUserId());
            return this.pstmt.executeUpdate() > 0;
        } finally {
            this.pstmt.close();
        }
    }

    /**
     * 得到Session Key
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    public String getSession(int userId) throws SQLException {
        ResultSet resultSet =
            this.conn.prepareStatement("select session_key from sessionkey where user_id = " + userId).executeQuery();
        resultSet.next();
        try {
            return resultSet.getString(1);
        } catch (SQLException e) {
            return null;
        }
    }
}
