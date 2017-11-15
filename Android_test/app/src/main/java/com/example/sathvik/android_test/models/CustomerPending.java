package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/15/2017.
 */

public class CustomerPending {
    String CustomerId;
    String CustomerName;
    String CustomerAdd;
    String email;
    String CustomerMob;

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAdd() {
        return CustomerAdd;
    }

    public void setCustomerAdd(String customerAdd) {
        CustomerAdd = customerAdd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerMob() {
        return CustomerMob;
    }

    public void setCustomerMob(String customerMob) {
        CustomerMob = customerMob;
    }

    @Override
    public String toString() {
        return "{" +
                "CustomerId='" + CustomerId + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerAdd='" + CustomerAdd + '\'' +
                ", email='" + email + '\'' +
                ", CustomerMob='" + CustomerMob + '\'' +
                '}';
    }
}
