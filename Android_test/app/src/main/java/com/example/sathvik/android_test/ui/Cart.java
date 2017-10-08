package com.example.sathvik.android_test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.models.SendToCart;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart extends AppCompatActivity {
    ListView cart_list;
    ArrayList<HashMap<String,String>> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cart_list = (ListView)findViewById(R.id.cart_list);
        SendToCart getFromCart = SendToCart.getInstance();

        cartList = getFromCart.getOrderList();
        String[] itemName = new String[cartList.size()];
        for(int i = 0; i < cartList.size();i++)
        {
            itemName[i] = cartList.get(i).get("ItemName");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemName);
        cart_list.setAdapter(adapter);
    }
}
