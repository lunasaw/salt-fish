package com.luna.saltfish.dao;

import com.luna.saltfish.tools.JdbcTemplate;
import com.luna.saltfish.entity.Goods;
import com.luna.saltfish.entity.Order;
import java.util.List;

/**
 * @author luna@mac
 */
public class OrderHandle {
    /**
     * 创建一个订单
     * 
     * @param order
     * @return
     * @throws Exception
     */
    public boolean doCreate(Order order) throws Exception {
        String sql = "INSERT INTO `order`(goods_id, user_id, date, message) VALUES (?, ?, ?, ?)";
        return JdbcTemplate.update(sql, order.getGoodsId(), order.getUserId(),
            new java.sql.Timestamp(order.getDate().getTime()), order.getMessage()) > 0;
    }

    /**
     * 通过用户id查询所有订单物品
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Goods> findGoodsByUser(int userId) throws Exception {
        String sql =
            "select id, num, content, type_id, image, producter_id, price, create_date,name from `goods` where id=any(SELECT goods_id from `order`  where user_id=?)";
        return JdbcTemplate.query(sql, new GoodsHandle().getBeanListHandler(), userId);

    }
}
