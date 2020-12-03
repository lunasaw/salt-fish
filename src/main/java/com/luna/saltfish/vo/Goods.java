package com.luna.saltfish.vo;

import java.util.Date;

/**
 * @author luna@mac
 */
public class Goods {

    /** 物品id */
    private Integer id;
    /** 图片路径 */
    private String image;
    /** 商品类型id */
    private Integer typeId;
    /** 物品名称 */
    private String name;
    /** 数量 */
    private Integer num;
    /** 物品价格 */
    private Float price;
    /** 物品介绍 */
    private String content;
    /** 发布者id */
    private Integer producterId;
    /** 状态 */
    private Integer states;
    /** 创建日期 */
    private Date creatDate;

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProducterId() {
        return producterId;
    }

    public void setProducterId(Integer producter_id) {
        this.producterId = producter_id;
    }

}

