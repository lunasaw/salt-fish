package com.luna.saltfish.dbHandle;

import com.luna.saltfish.dbc.DatabaseConnection;
import com.luna.saltfish.vo.Goods;
import com.luna.saltfish.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luna@mac
 */
public class ShopCartHandle {

    private Connection        conn  = null;
    private PreparedStatement pstmt = null;

    public ShopCartHandle() {
        try {
            this.conn = new DatabaseConnection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取user购物车物品数量
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Integer getShopCartNum(int id) throws Exception {
        String sql = "SELECT count(shop_id) FROM shoppingcart WHERE user_id= ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    /**
     * 检测是否已经加入购物车
     * 
     * @param userId
     * @param goodsId
     * @return 有记录返回true
     * @throws Exception
     */
    public boolean checkShoppingCart(int userId, int goodsId) throws Exception {
        try {
            String sql =
                "select count(shop_id) from shoppingcart where user_id= " + userId + " and goods_id= " + goodsId;
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = this.pstmt.executeQuery();
            rs.next();
            int anInt = rs.getInt(1);
            System.out.println(anInt);
            return anInt > 1;
        } finally {
            this.pstmt.close();
        }

    }

    /**
     * 增加一条购物车记录
     * 
     * @param goodsId
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean doSaveShoppingCart(int goodsId, int userId) throws Exception {
        System.out.println(checkShoppingCart(userId, goodsId));
        if (!checkShoppingCart(userId, goodsId)) {
            try {
                String sql = "INSERT INTO shoppingcart (goods_id, user_id) VALUES (?, ?)";
                this.pstmt = this.conn.prepareStatement(sql);
                pstmt.setInt(1, goodsId);
                pstmt.setInt(2, userId);
                return this.pstmt.executeUpdate() > 0;
            } finally {
                this.pstmt.close();
            }
        }
        return false;
    }

    /**
     * 查找user所有购物车内物品
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public List<Goods> findGoodsByUser(User user) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        String sql =
            "select id, num, content, type_id, image, producter_id, price, create_date, name from `goods` where id=any(SELECT goods_id from `shoppingcart`  where user_id=?) order by create_date desc";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, user.getId());
        return getGoods(all, this.pstmt);
    }

    public static List<Goods> getGoods(List<Goods> all, PreparedStatement pstmt) throws SQLException {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Goods good = new Goods();
            good.setId(rs.getInt(1));
            good.setNum(rs.getInt(2));
            good.setContent(rs.getString(3));
            good.setTypeId(rs.getInt(4));
            good.setImage(rs.getString(5));
            good.setProducterId(rs.getInt(6));
            good.setPrice(rs.getFloat(7));
            good.setName(rs.getString(9));
            java.sql.Timestamp timeStamp = rs.getTimestamp(8);
            java.util.Date date = new java.util.Date(timeStamp.getTime());
            good.setCreatDate(date);
            all.add(good);
        }
        rs.close();
        pstmt.close();
        return all;
    }

    /**
     * 移除购物车的一个物品
     * 
     * @param goodId
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean removeList(int goodId, int userId) throws Exception {
        try {
            String sql = "Delete from shoppingcart where goods_id=? and user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, goodId);
            pstmt.setInt(2, userId);
            return this.pstmt.executeUpdate() > 0;
        } finally {
            this.pstmt.close();
        }
    }

    /**
     * 清空user的购物车
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public boolean removeAllByUser(User user) throws Exception {
        try {
            String sql = "Delete from shoppingcart where and userId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            return this.pstmt.executeUpdate() > 0;
        } finally {
            this.pstmt.close();
        }
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
