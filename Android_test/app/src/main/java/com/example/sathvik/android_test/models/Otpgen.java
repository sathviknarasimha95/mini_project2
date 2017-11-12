package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/12/2017.
 */

public class Otpgen {
    String status;
    String OrderId;
    String Otp;

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

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", OrderId='" + OrderId + '\'' +
                ", Otp='" + Otp + '\'' +
                '}';
    }
}
