package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/11/2017.
 */

public class OrderStatus {
    private String status;
    private String OrderId;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", OrderId='" + OrderId + '\'' +
                '}';
    }
}
