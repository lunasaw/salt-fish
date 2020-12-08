package com.luna.saltfish.dao;

import com.luna.saltfish.tools.JdbcTemplate;
import com.luna.saltfish.entity.Goods;
import com.luna.saltfish.entity.User;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * @author luna@mac
 */
public class ShopCartHandle {

    /**
     * 获取user购物车物品数量
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Integer getShopCartNum(int id) throws Exception {
        String sql = "SELECT count(shop_id) FROM shoppingcart WHERE user_id= ?";
        return Integer.parseInt(JdbcTemplate.query(sql, new ScalarHandler<>(), id).toString());
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
        String sql =
            "select count(shop_id) from shoppingcart where user_id=? and goods_id=?";
        return Integer.parseInt(JdbcTemplate.query(sql, new ScalarHandler<>(), userId, goodsId).toString()) > 0;
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
        if (!checkShoppingCart(userId, goodsId)) {
            String sql = "INSERT INTO shoppingcart (goods_id, user_id) VALUES (?, ?)";
            return JdbcTemplate.update(sql, goodsId, userId) > 0;
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
        String sql =
            "select id, num, content, type_id, image, producter_id, price, create_date, name from `goods` where id=any(SELECT goods_id from `shoppingcart`  where user_id=?) order by create_date desc";
        return JdbcTemplate.query(sql, new GoodsHandle().getBeanListHandler(), user.getId());
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
        String sql = "Delete from shoppingcart where goods_id=? and user_id=?";
        return JdbcTemplate.update(sql, goodId, userId) > 0;
    }

    /**
     * 清空user的购物车
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean removeAllByUser(int userId) throws Exception {
        String sql = "Delete from shoppingcart where and userId=?";
        return JdbcTemplate.update(sql, userId) > 0;
    }
}
