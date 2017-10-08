package com.example.sathvik.android_test.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.Cartadapter;
import com.example.sathvik.android_test.models.SendToCart;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart extends BaseActivity {
    ListView cart_list;
    ArrayList<HashMap<String,String>> cartList;
    float total = 0;
    String[] itemName;
    String[] itemNo;
    String[] itemPrice;
    Cartadapter adapter;
    TextView totals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cart_list = (ListView)findViewById(R.id.cart_list);
        SendToCart getFromCart = SendToCart.getInstance();

        cartList = getFromCart.getOrderList();
        itemName = new String[cartList.size()];
        itemNo = new String[cartList.size()];
        itemPrice = new String[cartList.size()];
        for(int i = 0; i < cartList.size();i++)
        {
            itemName[i] = cartList.get(i).get("ItemName");
            itemNo[i] = cartList.get(i).get("No");
            itemPrice[i] = cartList.get(i).get("Price");
            total = total + Float.parseFloat(itemPrice[i]);
        }
        Log.i("Total=",total+"");
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemName);
        totals = (TextView)findViewById(R.id.total);
        adapter = new Cartadapter(getApplicationContext(),itemName,itemNo,itemPrice,total,totals);
        cart_list.setAdapter(adapter);
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}

