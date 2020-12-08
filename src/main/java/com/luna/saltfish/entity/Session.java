package com.luna.saltfish.entity;

/**
 * @author luna@mac
 * @className Session.java
 * @description TODO
 * @createTime 2020年12月04日 10:22:00
 */
public class Session {

    /**
     * session Id
     */
    private int    id;

    /**
     * session key
     */
    private String key;

    /** 用户Id */
    private int    userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
