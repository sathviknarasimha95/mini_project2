package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.Cartadapter;
import com.example.sathvik.android_test.api.OrderProducts;
import com.example.sathvik.android_test.models.PlaceOrder;
import com.example.sathvik.android_test.models.ProdMenu;
import com.example.sathvik.android_test.models.SendToCart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart extends BaseActivity {
    ListView cart_list;
    ArrayList<HashMap<String,String>> cartList;
    float total = 0;
    String[] itemName;
    String[] itemNo;
    String[] itemPrice;
    String[] itemId;
    Cartadapter adapter;
    TextView totals;
    ArrayList<String> itemNameList;
    ArrayList<String> itemNoList;
    ArrayList<String> itemPriceList;
    String OrderId = null;
    SendToCart sendToCart;
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
        itemId = new String[cartList.size()];
        itemPrice = new String[cartList.size()];
        for(int i = 0; i < cartList.size();i++)
        {
            itemName[i] = cartList.get(i).get("ItemName");
            itemNo[i] = cartList.get(i).get("No");
            itemPrice[i] = cartList.get(i).get("Price");
            itemId[i] = cartList.get(i).get("ItemId");
            total = total + Float.parseFloat(itemPrice[i]);
        }
        Log.i("Total=",total+"");
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemName);
        totals = (TextView)findViewById(R.id.total);
        itemNameList = new ArrayList<String>(Arrays.asList(itemName));
        itemNoList = new ArrayList<String>(Arrays.asList(itemNo));
        itemPriceList = new ArrayList<String>(Arrays.asList(itemPrice));
        adapter = new Cartadapter(getApplicationContext(),itemNameList,itemNoList,itemPriceList);
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
                            updateTotal();
                            adapter.notifyDataSetChanged();
                            finish();
                            overridePendingTransition( 0, 0);
                            startActivity(getIntent());
                            overridePendingTransition( 0, 0);
                        }
                        return true;
                    }
                });
                options.show();
                return true;
            }
        });
    updateTotal();
        findViewById(R.id.place_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView temp = (TextView)findViewById(R.id.total);
                float total = Float.parseFloat(temp.getText().toString());
                if(total == 0.0)
                {
                    Toast.makeText(getApplicationContext(),"Please Add items to Cart",Toast.LENGTH_LONG).show();
                }
                else {
                    placeorder();
                }
            }
        });
    }

    public void updateTotal()
    {
        totals = (TextView)findViewById(R.id.total);
        SendToCart sp = SendToCart.getInstance();
        float temp = sp.getTotal();
        totals.setText(temp+"");
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public Object getAdapter()
    {
        return  adapter;
    }

    protected  void placeorder()
    {
        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        int CustomerId = Integer.parseInt(LoginSharedPref.getString("CustomerId",defaultValue));
        //Toast.makeText(getApplicationContext(),CustomerId+"",Toast.LENGTH_SHORT).show();


            //OrderId  = UUID.randomUUID().toString().replaceAll("-","").subSequence(0,8);

            String OrderId = getToken(5);

            //int len = OrderId.length();
            //Toast.makeText(getApplicationContext(),OrderId+""+" "+len+"",Toast.LENGTH_SHORT).show();
            Date cDate = new Date();
            String fDate = new SimpleDateFormat("yyyy/MM/dd").format(cDate);
            //Toast.makeText(getApplicationContext(),fDate,Toast.LENGTH_SHORT).show();
            sendToCart = SendToCart.getInstance();
            float total = sendToCart.getTotal();
            //Toast.makeText(getApplicationContext(),total+"",Toast.LENGTH_SHORT).show();
            SendRequest(OrderId,CustomerId,fDate,total);

    }
    public void SendRequest(String OrderId,int CustomerId,String Date,float total)
    {
        Retrofit.Builder builder = new Retrofit.Builder()
                                    .baseUrl(getApplicationContext().getString(R.string.uri))
                                    .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        OrderProducts product = retrofit.create(OrderProducts.class);
        Call<PlaceOrder> call = product.placeOrder(
                OrderId,CustomerId,Date,total,itemName,itemPrice,itemId,itemNo
        );
        call.enqueue(new Callback<PlaceOrder>() {
            @Override
            public void onResponse(Call<PlaceOrder> call, Response<PlaceOrder> response) {
                Toast.makeText(getApplicationContext(),"Order Successfull",Toast.LENGTH_SHORT).show();
                PlaceOrder data = response.body();
                if(data.getStatus().toString().equals("Order_Successfull"))
                {
                    SendToCart sendToCart = SendToCart.getInstance();
                    sendToCart.cleardata();
                    Intent succssOrder = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(succssOrder);
                }

            }

            @Override
            public void onFailure(Call<PlaceOrder> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
            }
        });



    }
     public String getToken(int chars) {
        String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";
        String Token = "";
        for (int a = 1; a <= chars; a++) {
            Token += CharSet.charAt(new Random().nextInt(CharSet.length()));
        }
        return Token;
    }
}

