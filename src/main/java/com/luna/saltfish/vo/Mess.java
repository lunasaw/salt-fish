package com.luna.saltfish.vo;

import java.util.Date;

/**
 * @author luna@mac
 */
public class Mess {
    /** 消息发布者id */
    private int messFromId;
    /** 消息接收者id */
    private int messToId;
    /** 消息内容 */
    private String messText;
    /** 消息发送时间 */
    private Date   sendTime;
    /** 消息id */
    private int messId;
    /** 消息类型 */
    private int messType;

    public int getMessFromId() {
        return messFromId;
    }

    public void setMessFromId(int messFromId) {
        this.messFromId = messFromId;
    }

    public int getMessToId() {
        return messToId;
    }

    public void setMessToId(int messToId) {
        this.messToId = messToId;
    }

    public String getMessText() {
        return messText;
    }

    public void setMessText(String messText) {
        this.messText = messText;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getMessId() {
        return messId;
    }

    public void setMessId(int messId) {
        this.messId = messId;
    }

    public int getMessType() {
        return messType;
    }

    public void setMessType(int messType) {
        this.messType = messType;
    }


}
