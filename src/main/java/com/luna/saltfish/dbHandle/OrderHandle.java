package com.luna.saltfish.dbHandle;

import com.luna.saltfish.dbc.DatabaseConnection;
import com.luna.saltfish.vo.Goods;
import com.luna.saltfish.vo.Order;
import com.luna.saltfish.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class OrderHandle {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public OrderHandle() {
        try {
            this.conn = new DatabaseConnection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean doCreate(Order order) throws Exception {
        boolean flag = false;
        String sql = "INSERT INTO `order`(id,goods_id,user_id,date,message) VALUES (?,?,?,?,?)";
        Integer maxId = 0;
        try {
            ResultSet max_id = this.conn.prepareStatement("select max(id) from `order`").executeQuery();
            if (max_id.next()) {
                maxId = max_id.getInt("max(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, 1 + maxId);
        this.pstmt.setInt(2, order.getGoodsId());
        this.pstmt.setInt(3, order.getUserId());
        java.sql.Timestamp dateTime = new java.sql.Timestamp(order.getDate().getTime());
        pstmt.setTimestamp(4, dateTime);
        pstmt.setString(5, order.getMessage());
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }

        this.pstmt.close();
        return flag;
    }

    public List<Goods> findGoodsByUser(User user) throws Exception {
        int userId = user.getId();
        List<Goods> all = new ArrayList<Goods>();
        String sql = "select id,num,content,type_id,image,producter_id,price,create_date,name from `goods` where id=any(SELECT goods_id from `order`  where user_id=?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, userId);
        return ShopCartHandle.getGoods(all, this.pstmt);
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
