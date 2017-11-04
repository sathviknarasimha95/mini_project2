package com.example.sathvik.android_test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sathvik on 7/22/2017.
 */

public class Inventory {
    @SerializedName("ProductId")
    @Expose
    private String ProductId;
    @SerializedName("ProductName")
    @Expose
    private String ProductName;
    @SerializedName("ProductCat")
    @Expose
    private String ProductCat;
    @SerializedName("ProductType")
    @Expose
    private String ProductType;

    @SerializedName("ProductCompany")
    @Expose
    private String ProductCompany;

    @SerializedName("ProductPrice")
    @Expose

    private String ProductPrice;

    @SerializedName("ProductDesc")
    @Expose

    private String ProductDesc;

    @SerializedName("ProductUnit")
    @Expose

    private String ProductUnit;

    @Override
    public String toString() {
        return "{" +
                "ProductId=" + ProductId +
                ", ProductName='" + ProductName + '\'' +
                ", ProductCat='" + ProductCat + '\'' +
                ", ProductType='" + ProductType + '\'' +
                ", ProductCompany='" + ProductCompany + '\'' +
                ", ProductPrice='" + ProductPrice + '\'' +
                ", ProductDesc='" + ProductDesc + '\'' +
                ", ProductUnit='" + ProductUnit + '\'' +
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

    public String getProductCat() {
        return ProductCat;
    }

    public void setProductCat(String productCat) {
        ProductCat = productCat;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getProductCompany() {
        return ProductCompany;
    }

    public void setProductCompany(String productCompany) {
        ProductCompany = productCompany;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public String getProductUnit() {
        return ProductUnit;
    }

    public void setProductUnit(String productUnit) {
        ProductUnit = productUnit;
    }
}
