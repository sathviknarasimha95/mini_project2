package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/13/2017.
 */

public class PaymentHistory {
    String OrderId;
    String OrderPrice;
    String PaymentStatus;
    String PaymentType;
    String PaymentDate;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        OrderPrice = orderPrice;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "{" +
                "OrderId='" + OrderId + '\'' +
                ", OrderPrice='" + OrderPrice + '\'' +
                ", PaymentStatus='" + PaymentStatus + '\'' +
                ", PaymentType='" + PaymentType + '\'' +
                ", PaymentDate='" + PaymentDate + '\'' +
                '}';
    }
}
