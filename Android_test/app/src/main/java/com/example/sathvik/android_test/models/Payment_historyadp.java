package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/13/2017.
 */

public class Payment_historyadp {
    String OrderId;
    String OrderPrice;
    String PaymentStatus;
    String PaymentDate;
    String PaymentType;

    public Payment_historyadp(String orderId, String orderPrice, String paymentStatus, String paymentDate, String paymentType) {
        OrderId = orderId;
        OrderPrice = orderPrice;
        PaymentStatus = paymentStatus;
        PaymentDate = paymentDate;
        PaymentType = paymentType;
    }

    public String getOrderId() {
        return this.OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getOrderPrice() {
        return this.OrderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        OrderPrice = orderPrice;
    }

    public String getPaymentStatus() {
        return this.PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return this.PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getPaymentType() {
        return this.PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }
}
