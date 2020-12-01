package com.luna.saltfish.vo;

public class User {
    /** 用户id */
    private int id;
    /** 用户邮箱 */
    private String email;
    /** 用户密码 */
    private String pwd;
    /** 用户名称 */
    private String name;
    /** 用户 */
    private String stuNum;
    /** 用户id */
    private String qq;
    /** 用户id */
    private String phone;
    /** 用户id */
    private String img;

    public String getImg() {
        String userImg = img;
        if (userImg == null || userImg.length() == 0) {
            return "static/user_img/0";
        } else {
            return userImg;
        }
    }

    public void setImg(String img) {
        this.img = img;
    }

    private int messnum;


    public int getMessnum() {
        return messnum;
    }

    public void setMessnum(int messnum) {
        this.messnum = messnum;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String tmp) {
        name = tmp;
    }

    public void setEmail(String tmp) {
        email = tmp;
    }

    public void setId(int tmp) {
        id = tmp;
    }

    public void setPwd(String tmp) {
        pwd = tmp;
    }

    public void setStuNum(String tmp) {
        stuNum = tmp;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public int getId() {
        return id;
    }

    public String getStuNum() {
        return stuNum;
    }

}
