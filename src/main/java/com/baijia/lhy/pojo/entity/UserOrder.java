package com.baijia.lhy.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_order")
public class UserOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;

    /**
     * 订单总金额
     */
    private Double allCost;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 付款时间
     */
    private LocalDateTime payTime;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 订单状态: 待付款，待收货,待评价，已退款，已取消
     */
    private String status;

    /**
     * 实际付款金额
     */
    private Double actualPayment;

    /**
     * 总减免金额
     */
    private Double totalDeduction;


}
