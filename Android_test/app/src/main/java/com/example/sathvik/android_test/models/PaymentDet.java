package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/12/2017.
 */

public class PaymentDet {
    private String OrderId;
    private String OrderPrice;
    private String Date;
    private String OrderStatus;
    private String PaymentStatus;

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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "OrderId='" + OrderId + '\'' +
                ", OrderPrice='" + OrderPrice + '\'' +
                ", Date='" + Date + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", PaymentStatus='" + PaymentStatus + '\'' +
                '}';
    }
}
