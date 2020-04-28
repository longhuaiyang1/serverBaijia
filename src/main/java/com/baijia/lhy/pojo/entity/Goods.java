package com.baijia.lhy.pojo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID=1000L;

    /**
     * 商品id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品类型id
     */
    private Integer goodsTypeId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品图片地址
     */
    private String img;

    /**
     * 以前价格
     */
    private Double oldPrice;

    /**
     * 当前价格
     */
    private Double price;

    /**
     * 是否在售:  YES 或 NO
     */
    private String onSale;

    /**
     * 商品的详情
     */
    private String detail;

    /**
     * 库存
     */
    private Integer count;


}
