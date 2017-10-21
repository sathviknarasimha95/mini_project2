package com.example.sathvik.android_test.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sathvik on 10/7/2017.
 */

//this is a singleton class

public class SendToCart {
    private static SendToCart sendToCart;
    public ArrayList<HashMap<String, String>> orderList = new ArrayList<HashMap<String, String>>();;

    private SendToCart()
    {
        if (sendToCart != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static SendToCart getInstance() {
        if (sendToCart == null) { //if there is no instance available... create new one
            synchronized (SendToCart.class) {
                if (sendToCart == null) sendToCart = new SendToCart();
            }
        }
        return sendToCart;
    }

    public void setOrderList(HashMap mp)
    {
        orderList.add(mp);
    }

    public void removeOrder(int position)
    {
        orderList.remove(position);
    }

    public ArrayList<HashMap<String,String>> getOrderList()
    {
        return orderList;
    }

    public void cleardata()
    {
        orderList.clear();
    }

}
