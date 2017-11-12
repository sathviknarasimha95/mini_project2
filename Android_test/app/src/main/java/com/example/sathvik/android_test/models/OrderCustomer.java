package com.example.sathvik.android_test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sathvik on 11/3/2017.
 */

public class OrderCustomer {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("OrderId")
    @Expose
    private String OrderId;
    @SerializedName("CustomerId")
    @Expose
    private int CustomerId;
    @SerializedName("Date")
    @Expose
    private String Date;
    @SerializedName("OrderPrice")
    @Expose
    private float OrderPrice;

    @SerializedName("OrderStatus")
    @Expose
    private String OrderStatus;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;
    @SerializedName("PaymentStatus")
    @Expose
    private String PaymentStatus;

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public float getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        OrderPrice = orderPrice;
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
                "id=" + id +
                ", OrderId='" + OrderId + '\'' +
                ", CustomerId=" + CustomerId +
                ", Date='" + Date + '\'' +
                ", OrderPrice=" + OrderPrice +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", PaymentStatus='" + PaymentStatus + '\'' +
                '}';
    }
}
