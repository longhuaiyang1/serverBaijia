package com.baijia.lhy.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单详情主键id
     */
    @TableId(value = "order_info_id", type = IdType.AUTO)
    private Integer orderInfoId;

    /**
     * 外键哪个订单的id
     */
    private String orderId;

    /**
     * 原来的商品的连接
     */
    private Integer originGoodId;

    /**
     * 下单时候的商品价格
     */
    private Double price;

    /**
     * 这个商品当时的下单数量
     */
    private Integer count;

    /**
     * 下单时候商品的图片
     */
    private String img;

    /**
     * 下单时候的原价
     */
    private Double oldPrice;


}
