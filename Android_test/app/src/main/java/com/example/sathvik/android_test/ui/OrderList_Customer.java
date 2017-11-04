package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.GetorderscustAdapter;
import com.example.sathvik.android_test.api.GetOrders;
import com.example.sathvik.android_test.models.Inventory;
import com.example.sathvik.android_test.models.OrderCustomer;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderList_Customer extends AppCompatActivity {
    ListView golv;
    ArrayAdapter<String> adapter;
    String FileName = "Login_fine";
    String[] OrderIds;
    String[] date;
    float[] price;
    String[] OrderStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list__customer);

        golv = (ListView)findViewById(R.id.get_order_customer);
        Context context;
        //golv.setAdapter(adapter);
        golv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent orderdetails = new Intent(getApplicationContext(),OrderDetailCustomer.class);
                orderdetails.putExtra("OrderId", OrderIds[i]);
                startActivity(orderdetails);
            }
        });
        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        int CustomerId = Integer.parseInt(LoginSharedPref.getString("CustomerId",defaultValue));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetOrders getOrders = retrofit.create(GetOrders.class);
        Call<List<OrderCustomer>> getordercustomer = getOrders.getOrder(CustomerId);

        getordercustomer.enqueue(new Callback<List<OrderCustomer>>() {
            @Override
            public void onResponse(Call<List<OrderCustomer>> call, Response<List<OrderCustomer>> response) {
                //Toast.makeText(getApplicationContext(),"Yeah",Toast.LENGTH_LONG).show();
                int j = response.body().size();
                OrderIds = new String[response.body().size()];
                  date = new String[response.body().size()];
                  price = new float[response.body().size()];
                  OrderStatus = new String[response.body().size()];

                int i=0;
                for (OrderCustomer getorder : response.body()) {
                    //Log.i("Order", getorder.getCustomerId()+"");
                    OrderIds[i] = getorder.getOrderId();

                    date[i] = getorder.getDate();

                    price[i] = getorder.getOrderPrice();
                    OrderStatus[i] = getorder.getOrderStatus();
                    //Log.i("getOrder=",price[i]+"");
                    i++;
                }
                //Log.i("datasat=",price[1]+"");
                //Toast.makeText(getApplicationContext(),CustomerIds[1]+"",Toast.LENGTH_LONG).show();
                GetorderscustAdapter gca = new GetorderscustAdapter(getApplicationContext(),OrderIds,date,price,OrderStatus);
                golv.setAdapter(gca);
            }

            @Override
            public void onFailure(Call<List<OrderCustomer>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_LONG).show();
            }
        });
    }
}
