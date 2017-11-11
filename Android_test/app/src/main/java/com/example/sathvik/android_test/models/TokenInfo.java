package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/11/2017.
 */

public class TokenInfo {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                '}';
    }
}
