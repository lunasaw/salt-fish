package com.luna.saltfish;

import com.luna.saltfish.constant.GoodsStatusConstant;
import com.luna.saltfish.tools.JdbcTemplate;
import com.luna.saltfish.entity.Goods;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author luna@mac
 * @className Test.java
 * @description TODO
 * @createTime 2020年12月07日 17:21:00
 */
public class Test {

    public static void main(String[] args) throws SQLException {
        String sql =
            "SELECT id, num, content, type_id, image, producter_id, price, create_date, name from goods  where status=? order by create_date desc limit ?,?";
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        List<Goods> query = JdbcTemplate.query(sql, new BeanListHandler<Goods>(Goods.class, processor),
            GoodsStatusConstant.REVIEW_ED, 2, 10);
        for (Goods so : query) {
            System.out.println(so.getCreateDate());
        }
    }
}
