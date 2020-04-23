package com.baijia.lhy.pojo.entity;

public class GoodsType {
    private Integer goodsTypeId;

    private Integer parentTypeId;

    private String typeName;

    private Integer sort;

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public Integer getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Integer parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}