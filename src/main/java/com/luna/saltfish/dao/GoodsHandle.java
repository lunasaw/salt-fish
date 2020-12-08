package com.luna.saltfish.dao;

import com.luna.saltfish.constant.GoodsStatusConstant;
import com.luna.saltfish.tools.IntHolder;
import com.luna.saltfish.tools.JdbcTemplate;
import com.luna.saltfish.entity.Goods;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luna@mac
 */
public class GoodsHandle {

    private BeanHandler<Goods>     beanHandler;

    private BeanListHandler<Goods> beanListHandler;

    public BeanHandler<Goods> getBeanHandler() {

        if (beanHandler == null) {
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            return new BeanHandler<Goods>(Goods.class, processor);
        } else {
            return beanHandler;
        }
    }

    public void setBeanHandler(BeanHandler<Goods> beanHandler) {
        this.beanHandler = beanHandler;
    }

    public BeanListHandler<Goods> getBeanListHandler() {
        if (beanListHandler == null) {
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            return new BeanListHandler<Goods>(Goods.class, processor);
        } else {
            return beanListHandler;
        }
    }

    public void setBeanListHandler(BeanListHandler<Goods> beanListHandler) {
        this.beanListHandler = beanListHandler;
    }

    /**
     * 商品id查询商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Goods findById(int id) throws Exception {
        String sql =
            "SELECT id,image,type_id,name,num,price,status,content,producter_id,create_date FROM goods WHERE id=?";
        return JdbcTemplate.query(sql, getBeanHandler(), id);
    }

    /**
     * 创建一个商品
     * 
     * @param good
     * @return
     * @throws Exception
     */
    public boolean doCreate(Goods good) throws Exception {
        String sql =
            "INSERT INTO `goods`(name, price, image, content, status, id, type_id, producter_id, create_date, num) VALUES (?, ?, ?, ? ,?, ?, ?, ?, ?, ?)";

        return JdbcTemplate.update(sql, good.getName(), good.getPrice(), good.getImage(), good.getContent(),
            GoodsStatusConstant.PRE_VIEW, good.getId(), good.getTypeId(), good.getProducterId(),
            new Timestamp(good.getCreateDate().getTime()), 1) > 0;
    }

    public int getMaxId() throws Exception {
        String sql = "select max(id) from goods";
        return JdbcTemplate.query(sql, new ScalarHandler<Integer>());
    }

    /**
     * 查询所有物品
     * 
     * @param num
     * @param limitMin 开始记录
     * @param perPage 记录数
     * @return
     * @throws Exception
     */
    public List<Goods> findAll(IntHolder num, int limitMin, int perPage) throws Exception {
        String sql =
            "SELECT id, num, content, type_id, image, producter_id, price, create_date, name from goods  where status=? order by create_date desc limit ?,?";
        List<Goods> queryGoodsList =
            JdbcTemplate.query(sql, getBeanListHandler(), GoodsStatusConstant.REVIEW_ED,
                limitMin,
                perPage);
        num.value = queryGoodsList.size();
        return queryGoodsList;
    }

    /**
     * 查询所有待审核商品
     * 
     * @return
     * @throws Exception
     */
    public List<Goods> findAllNotAuditing() throws Exception {
        String sql =
            "SELECT id, num, content, type_id, image, producter_id, price, name, create_date from goods where status=?";
        return JdbcTemplate.query(sql, getBeanListHandler(), GoodsStatusConstant.PRE_VIEW);
    }

    /**
     * 更新物品信息
     * 
     * @param good
     * @return
     * @throws Exception
     */
    public boolean doUpdate(Goods good) throws Exception {
        StringBuilder sql = new StringBuilder("update goods");
        List<Object> params = new ArrayList<>();
        StringBuilder option = new StringBuilder(" set ");
        if (good != null) {
            if (good.getImage() != null) {
                option.append("image=?,");
                params.add(good.getImage());
            }
            if (good.getTypeId() != null) {
                option.append("type_id=?,");
                params.add(good.getTypeId());
            }
            if (good.getName() != null) {
                option.append("name=?,");
                params.add(good.getName());
            }
            if (good.getNum() != null) {
                option.append("num=?,");
                params.add(good.getNum());
            }
            if (good.getPrice() != null) {
                option.append("price=?,");
                params.add(good.getPrice());
            }
            if (good.getStatus() != null) {
                option.append("status=?,");
                params.add(good.getStatus());
            }
            if (good.getContent() != null) {
                option.append("content=?,");
                params.add(good.getContent());
            }
            if (good.getProducterId() != null) {
                option.append("producter_id=?,");
                params.add(good.getProducterId());
            }
            if (good.getCreateDate() != null) {
                option.append("create_date=?,");
                params.add(good.getCreateDate());
            }
        }
        if (option.length() > 4) {
            option.setLength(option.length() - 1);
            sql.append(option);
        }
        sql.append("where id=?");
        if (good != null) {
            params.add(good.getId());
        }
        System.out.println(sql);
        Object[] param = new Object[params.size()];
        for (int i = 0; i < param.length; i++) {
            param[i] = params.get(i);
        }
        return JdbcTemplate.update(sql.toString(), param) > 0;

    }

    /**
     * 查找指定类别的商品
     * 
     * @param cetaId 商品类别
     * @param num
     * @param limitMin 起始
     * @param perPage 记录数
     * @return
     * @throws Exception
     */
    public List<Goods> findByCeta(int cetaId, IntHolder num, int limitMin, int perPage) throws Exception {
        String sql =
            "select id, num, content, type_id, image, producter_id, price, name, create_date from goods where status=? and type_id=? order by create_date desc limit ?,?";

        List<Goods> goodsList =
            JdbcTemplate.query(sql, getBeanListHandler(), GoodsStatusConstant.REVIEW_ED, cetaId, limitMin, perPage);
        num.value = goodsList.size();
        return goodsList;
    }

    /**
     * 通过关键字查询
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public List<Goods> findByKey(String key) throws Exception {
        String sql =
            "SELECT id, num, content, type_id, image, producter_id, price, name, create_date from goods where status=? and name like ? order by create_date desc";
        return JdbcTemplate.query(sql, getBeanListHandler(), GoodsStatusConstant.REVIEW_ED, "%" + key + "%");

    }

    /**
     * 查询用户发布的物品
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Goods> findByUserId(int userId) throws Exception {
        String sql =
            "SELECT id, num, content, type_id, image, producter_id, price, name, create_date, status from goods where (status=? or status=?) and producter_id=? order by create_date desc";

        return JdbcTemplate.query(sql, getBeanListHandler(), GoodsStatusConstant.REVIEW_ED, GoodsStatusConstant.TRADING,
            userId);
    }
}
