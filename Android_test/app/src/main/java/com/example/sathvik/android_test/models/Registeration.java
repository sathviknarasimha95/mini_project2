package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/15/2017.
 */

public class Registeration {
    private String status_reg;

    public String getStatus() {
        return status_reg;
    }

    public void setStatus(String status) {
        this.status_reg = status;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status_reg + '\'' +
                '}';
    }
}
