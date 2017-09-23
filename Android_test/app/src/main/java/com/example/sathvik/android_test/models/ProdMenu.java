package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 9/22/2017.
 */

public class ProdMenu {
    private String name;
    private int thumbnail;

    public ProdMenu() {
    }

    public ProdMenu(String name, int thumbnail) {
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

