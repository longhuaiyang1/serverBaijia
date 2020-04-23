package com.baijia.lhy.pojo.entity;

import java.util.Date;

public class UserOrder {
    private String orderId;

    private Double allCost;

    private Date createTime;

    private Date payTime;

    private String receiverPhone;

    private String receiverName;

    private String receiveAddress;

    private String payType;

    private String status;

    private Double actualPayment;

    private Double totalDeduction;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Double getAllCost() {
        return allCost;
    }

    public void setAllCost(Double allCost) {
        this.allCost = allCost;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(Double actualPayment) {
        this.actualPayment = actualPayment;
    }

    public Double getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(Double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }
}