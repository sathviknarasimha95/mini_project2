package com.example.sathvik.android_test.models;

/**
 * Created by sathvik on 11/14/2017.
 */

public class Orderadp {
    String prod_name;
    String prod_id;
    String prod_type;
    String prod_company;
    String prod_price;

    public Orderadp(String prod_name, String prod_id, String prod_type, String prod_company, String prod_price) {
        this.prod_name = prod_name;
        this.prod_id = prod_id;
        this.prod_type = prod_type;
        this.prod_company = prod_company;
        this.prod_price = prod_price;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }

    public String getProd_company() {
        return prod_company;
    }

    public void setProd_company(String prod_company) {
        this.prod_company = prod_company;
    }

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }
}
