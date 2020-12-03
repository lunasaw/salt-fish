package com.luna.saltfish.vo;

import java.util.Date;

/**
 * @author luna@mac
 */
public class Order {
    /** 记录id */
    private int id;
    /** 物品id */
    private int goodsId;
    /** 用户id */
    private int userId;
    /** 产生日期 */
    private Date date;
    /** 记录消息 */
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
