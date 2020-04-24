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
@TableName("goods_type")
public class GoodsType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品类型id
     */
    @TableId(value = "goods_type_id", type = IdType.AUTO)
    private Integer goodsTypeId;

    /**
     * 商品父类型id
     */
    private Integer parentTypeId;

    /**
     * 商品类型名称
     */
    private String typeName;

    /**
     * 商品排序序号，根据序号排序优先展示
     */
    private Integer sort;


}
