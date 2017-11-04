package com.example.sathvik.android_test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.GetOrderDetcAdapter;
import com.example.sathvik.android_test.api.GetOrderDetails;
import com.example.sathvik.android_test.models.OrderCustomerDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderDetailCustomer extends AppCompatActivity {
    ListView godc;
    ArrayAdapter<String> adapter;
    String[] ProductId;
    String[] ProductName;
    String[] UnitPrice;
    int[] ProductNo;
    String OrderPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_customer);
        Bundle bundle = getIntent().getExtras();
        String OrderId = bundle.getString("OrderId");
        //TextView tv = (TextView)findViewById(R.id.OrderIddet);
        //tv.setText(OrderId);
        godc = (ListView) findViewById(R.id.get_order_detail_customer);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetOrderDetails getOrderDetails = retrofit.create(GetOrderDetails.class);
        Call<List<OrderCustomerDetails>> getordercustomerdetails = getOrderDetails.getOrderDetails(OrderId);

        getordercustomerdetails.enqueue(new Callback<List<OrderCustomerDetails>>() {
            @Override
            public void onResponse(Call<List<OrderCustomerDetails>> call, Response<List<OrderCustomerDetails>> response) {
                //Toast.makeText(getApplicationContext(),"Yeah",Toast.LENGTH_SHORT).show();
                ProductId = new String[response.body().size()];
                ProductName = new String[response.body().size()];
                UnitPrice = new String[response.body().size()];
                ProductNo = new int[response.body().size()];
                int i=0;
                for (OrderCustomerDetails getorder : response.body()) {
                    //Log.i("Order", getorder.getCustomerId()+"");
                    ProductId[i] = getorder.getProductId();
                    ProductName[i] = getorder.getProductName();
                    UnitPrice[i] = getorder.getUnitPrice();
                    ProductNo[i] = getorder.getProductNo();
                    OrderPrice = getorder.getOrderPrice();
                    i++;
                }
                GetOrderDetcAdapter getOrderDetcAdapter = new GetOrderDetcAdapter(getApplicationContext(),ProductName,ProductNo,UnitPrice,OrderPrice);
                godc.setAdapter(getOrderDetcAdapter);
                TextView t = (TextView)findViewById(R.id.total_det);
                t.setText("Total="+OrderPrice);
            }

            @Override
            public void onFailure(Call<List<OrderCustomerDetails>> call, Throwable t) {
                //Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
