package com.baijia.lhy.pojo.dto;

import com.baijia.lhy.pojo.entity.Goods;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class ShopCarGoods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 购物车id
     */
    @TableId(value = "shop_car_id", type = IdType.AUTO)
    private Integer shopCarId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品数量
     */
    private Integer shopCarCount;

    //商品
    private Goods goods;
}
