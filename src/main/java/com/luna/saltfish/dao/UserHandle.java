package com.luna.saltfish.dao;

import com.luna.saltfish.tools.JdbcTemplate;
import com.luna.saltfish.entity.User;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * @author luna@mac
 */
public class UserHandle {

    private BeanHandler<User>     beanHandler;

    private BeanListHandler<User> beanListHandler;

    public BeanHandler<User> getBeanHandler() {

        if (beanHandler == null) {
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            return new BeanHandler<User>(User.class, processor);
        } else {
            return beanHandler;
        }
    }

    public void setBeanHandler(BeanHandler<User> beanHandler) {
        this.beanHandler = beanHandler;
    }

    public BeanListHandler<User> getBeanListHandler() {
        if (beanListHandler == null) {
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            return new BeanListHandler<User>(User.class, processor);
        } else {
            return beanListHandler;
        }
    }

    public void setBeanListHandler(BeanListHandler<User> beanListHandler) {
        this.beanListHandler = beanListHandler;
    }

    /**
     * 创建一个用户
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public boolean doCreate(User user) throws Exception {
        String sql = "INSERT INTO user(email, pwd, name, stu_num, phone) VALUES (?, ?, ?, ?, ?)";
        return JdbcTemplate.update(sql, user.getEmail(), user.getPwd(), user.getName(), user.getStuNum(),
            user.getPhone()) > 0;

    }

    /**
     * 更新用户信息
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public boolean doUpdate(User user) throws Exception {
        String sql = "update user set pwd=?, name=?, phone=?, img=? where id=?";
        return JdbcTemplate.update(sql, user.getPwd(), user.getName(), user.getPhone(), user.getImg(),
            user.getId()) > 0;

    }

    /**
     * 关键字查询用户信息
     * 
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<User> findAll(String keyWord) throws Exception {
        String sql = "SELECT id, email, pwd, name, stu_num FROM user WHERE name LIKE ? OR email LIKE ?";
        return JdbcTemplate.query(sql, getBeanListHandler(), "%" + keyWord + "%", "%" + keyWord + "%");
    }

    /**
     * 清空用户消息未读
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public void emptyMessnum(User user) throws Exception {
        String sql = "update user set mess_num=0 WHERE id=?";
        JdbcTemplate.update(sql, user.getId());
    }

    /**
     * 通过id返回用户
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public User findById(int id) throws Exception {
        String sql = "SELECT id, email, pwd, name, stu_num, phone, mess_num, img FROM user WHERE id=?";
        return JdbcTemplate.query(sql, getBeanHandler(), id);
    }

    /**
     * 通过邮箱查找用户
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public User findByEmail(String str) throws Exception {
        String sql = "SELECT id,email, pwd, name, stu_num, phone, mess_num, img FROM user WHERE email=?";
        return JdbcTemplate.query(sql, new BeanHandler<User>(User.class), str);
    }
}