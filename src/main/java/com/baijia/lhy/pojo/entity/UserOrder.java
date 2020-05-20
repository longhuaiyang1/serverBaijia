package com.baijia.lhy.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lhy
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_order")
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收货门店id
     */
    private Integer receivePointId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 支付方式: cash货到付款  wx微信支付 zfb支付宝支付
     */
    private String payType;

    /**
     * 预估总金额
     */
    private Double estimateTotalCost;

    /**
     * 实际付款总金额
     */
    private Double actualTotalCost;

    /**
     * 订单状态:  0待收货未付款，1待收货已付款,2已收货和付款，3待评价，-1已取消
     */
    private Integer status;

    /**
     * 付款时间
     */
    private LocalDateTime payTime;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 计划收货日期
     */
    private LocalDate planReceiveTime;

    /**
     * 实际收货时间
     */
    private LocalDate receiveTime;

}
