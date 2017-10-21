package com.example.sathvik.android_test.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<String> itemNameList;
    ArrayList<String> itemNoList;
    ArrayList<String> itemPriceList;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cart_list = (ListView)findViewById(R.id.cart_list);
        final SendToCart getFromCart = SendToCart.getInstance();

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
        itemNameList = new ArrayList<String>(Arrays.asList(itemName));
        itemNoList = new ArrayList<String>(Arrays.asList(itemNo));
        itemPriceList = new ArrayList<String>(Arrays.asList(itemPrice));
        adapter = new Cartadapter(getApplicationContext(),itemNameList,itemNoList,itemPriceList,total,totals);
        cart_list.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        cart_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu options = new PopupMenu(Cart.this,view);

                pos = i;
                options.getMenuInflater().inflate(R.menu.options_in_cart,options.getMenu());
                options.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String todo = item.getTitle().toString();
                        if(todo.equals("add"))
                        {
                            
                        }
                        else if(todo.equals("remove"))
                        {

                            adapter.remove(adapter.getItem(pos));
                            getFromCart.removeOrder(pos);
                            adapter.notifyDataSetChanged();

                        }
                        return true;
                    }
                });
                options.show();

                return true;
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public Object getAdapter()
    {
        return  adapter;
    }
}

