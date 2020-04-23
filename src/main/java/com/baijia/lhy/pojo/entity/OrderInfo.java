package com.baijia.lhy.pojo.entity;

public class OrderInfo {
    private Integer orderInfoId;

    private String orderId;

    private Integer originGoodId;

    private Double price;

    private Integer count;

    private String img;

    private Double oldPrice;

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getOriginGoodId() {
        return originGoodId;
    }

    public void setOriginGoodId(Integer originGoodId) {
        this.originGoodId = originGoodId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }
}