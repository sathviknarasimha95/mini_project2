package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/15/2017.
 */

public class Updatepending {
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
