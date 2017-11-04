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
        String t = mp.get("ItemName").toString();
        float price = Float.parseFloat(mp.get("Price").toString());
        int no = Integer.parseInt(mp.get("No").toString());
        if(!updateOrderList(t,price,no)) {
            orderList.add(mp);
        }


    }
    public boolean updateOrderList(String t,float price,int no)
    {
        int flag=0;
        for(int i = 0;i<orderList.size();i++)
        {
            System.out.println("Hashmap="+orderList.get(i));
            System.out.println("Hashmapt="+t);
            if(orderList.get(i).containsValue(t))
            {
                float temps = Float.parseFloat(orderList.get(i).get("Price")) + price;
                orderList.get(i).put("Price",temps+"");
                //orderList.get(i).replace("Price",temps+"");
                //orderList.get(i).replace("Price",temps+"");
                int tem = Integer.parseInt(orderList.get(i).get("No")) + no;
                orderList.get(i).put("No",tem+"");
                flag=1;
            }
        }
        if(flag==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void removeOrder(int position)
    {
        orderList.remove(position);
    }

    public ArrayList<HashMap<String,String>> getOrderList()
    {
        return orderList;
    }

    public float getTotal()
    {
        float total = 0;
        for(int i =0;i<orderList.size();i++)
        {
            String temp = orderList.get(i).get("Price");
            total = total+Float.parseFloat(temp);
        }
        return total;
    }

    public void cleardata()
    {
        orderList.clear();
    }

}
