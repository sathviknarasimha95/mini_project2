package com.example.sathvik.android_test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sathvik on 11/4/2017.
 */

public class OrderCustomerDetails {
    @SerializedName("ProductId")
    @Expose
    private String ProductId;
    @SerializedName("ProductName")
    @Expose
    private String ProductName;
    @SerializedName("UnitPrice")
    @Expose
    private String UnitPrice;
    @SerializedName("OrderPrice")
    @Expose
    private String OrderPrice;
    @SerializedName("ProductNo")
    @Expose
    private int ProductNo;

    @Override
    public String toString() {
        return "{" +
                "ProductId='" + ProductId + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", UnitPrice='" + UnitPrice + '\'' +
                ", OrderPrice='" + OrderPrice + '\'' +
                ", ProductNo=" + ProductNo +
                '}';
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        OrderPrice = orderPrice;
    }

    public int getProductNo() {
        return ProductNo;
    }

    public void setProductNo(int productNo) {
        ProductNo = productNo;
    }
}
