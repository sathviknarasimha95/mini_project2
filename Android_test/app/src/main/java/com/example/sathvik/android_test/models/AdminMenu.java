package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 10/31/2017.
 */

public class AdminMenu {
    private String name;
    private int thumbnail;

    public AdminMenu() {
    }

    public AdminMenu(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
