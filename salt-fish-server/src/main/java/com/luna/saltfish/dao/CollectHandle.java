package com.luna.saltfish.dao;

import com.luna.saltfish.entity.Goods;
import com.luna.saltfish.entity.User;
import com.luna.saltfish.tools.IntHolder;
import com.luna.saltfish.tools.JdbcTemplate;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author luna@mac
 */
public class CollectHandle {
    /**
     * 检测是否已经加入收藏
     * 
     * @param userId
     * @param goodsId
     * @return
     * @throws Exception
     */
    public boolean checkCollect(int userId, int goodsId) throws SQLException {
        String sql = "select user_id from `collect` where user_id=? and goods_id=?";
        return JdbcTemplate.query(sql, new ScalarHandler<Integer>(), userId, goodsId) != null;
    }

    /**
     * 收藏物品
     * 
     * @param userId 用户id
     * @param goodsId 物品id
     * @return
     * @throws Exception
     */
    public boolean doCreate(int userId, int goodsId) throws Exception {
        String sql = "insert into `collect` (user_id, goods_id) values (?,?)";
        return JdbcTemplate.update(sql, userId, goodsId) > 0;
    }

    /**
     * 查找user所有收藏夹内物品
     *
     * @param user 用户
     * @param num
     * @param limitMin
     * @param perPage
     * @return
     * @throws Exception
     */
    public List<Goods> findGoodsByUser(User user, IntHolder num, int limitMin, int perPage) throws Exception {
        String sql =
            "select id, num, content, type_id, image, producter_id, price, create_date, name, status from `goods` where id = any(SELECT goods_id from `collect`  where user_id=?) order by create_date desc limit ?,?";
        GoodsHandle goodsHandle = new GoodsHandle();
        List<Goods> goodsList =
            JdbcTemplate.query(sql, goodsHandle.getBeanListHandler(), user.getId(), limitMin, perPage);
        String count = "SELECT count(*) from goods  where id = any(SELECT goods_id from `collect`  where user_id=?)";
        num.value = Integer
            .parseInt(JdbcTemplate.query(count, new ScalarHandler<>(), user.getId()).toString());
        return goodsList;
    }

    /**
     * 移除一个收藏夹物品
     * 
     * @param goodsId
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean removeOneFromCollect(int goodsId, int userId) throws Exception {
        String sql = "Delete from `collect` where goods_id=? and user_id=?";
        return JdbcTemplate.update(sql, goodsId, userId) > 0;
    }

}
