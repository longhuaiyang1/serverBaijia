package com.baijia.lhy.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("receive_point")
public class ReceivePoint implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 提货点标题
     */
    private String title;

    /**
     * 提货点描述
     */
    private String detail;

    /**
     * 头像
     */
    @TableField("head_url")
    private String headUrl;

    /**
     * 精度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 门店状态： 0表示门店经营中，-1表示门店已废弃
     */
    private int status;

}
