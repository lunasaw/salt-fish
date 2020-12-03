package com.luna.saltfish.constant;

/**
 * @author luna
 * @className GoodsStatusConstant.java
 * @description TODO
 * @createTime 2020年12月03日 08:38:00
 */
public interface GoodsStatusConstant {
    /** 待审核 */
    Integer PRE_VIEW    = 1;

    /** 审核通过 */
    Integer REVIEW_ED   = 2;

    /** 审核未通过 */
    Integer REVIEW_FAIL = 3;

    /** 已被购买(交易中) */
    Integer TRADING     = 4;

    /** 交易完成 */
    Integer COMPLETION  = 5;

}
