package com.example.sathvik.android_test.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.GetOrderAdminAdapter;
import com.example.sathvik.android_test.api.GetOrderAdmin;
import com.example.sathvik.android_test.api.GetOrders;
import com.example.sathvik.android_test.models.OrderCustomer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewOrderAdmin extends AppCompatActivity {
    ListView voadmin;
    String[] CustomerName;
    String[] OrderId;
    String[] date;
    float[] OrderPrice;
    String[] OrderStatus;
    String[] PaymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_admin);
        Bundle bundle = getIntent().getExtras();
        String status = bundle.getString("OrderStatus");
        voadmin = (ListView) findViewById(R.id.view_order_admin);
        if(status.equals("All"))
        {

        }
        else {
            voadmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent orderdetails = new Intent(getApplicationContext(), OrderDetailCustomer.class);
                    orderdetails.putExtra("OrderId", OrderId[i]);
                    orderdetails.putExtra("PaymentStatus", PaymentStatus[i]);
                    orderdetails.putExtra("Status", status);
                    startActivity(orderdetails);
                }
            });
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //GetOrders getOrdersAdmin = retrofit.create(GetOrders.class);
        GetOrderAdmin getOrdersAdmin = retrofit.create(GetOrderAdmin.class);
        Call<List<OrderCustomer>> getorderadmin = getOrdersAdmin.getOrderAdmin(status);
        getorderadmin.enqueue(new Callback<List<OrderCustomer>>() {
            @Override
            public void onResponse(Call<List<OrderCustomer>> call, Response<List<OrderCustomer>> response) {
                //
                CustomerName = new String[response.body().size()];
                OrderId = new String[response.body().size()];
                date = new String[response.body().size()];
                OrderPrice = new float[response.body().size()];
                OrderStatus = new String[response.body().size()];
                PaymentStatus = new String[response.body().size()];
                int i=0;
                for (OrderCustomer getorderadmin : response.body()) {
                    //Log.i("Order", getorder.getCustomerId()+"");
                    CustomerName[i] = getorderadmin.getCustomerName();
                    OrderId[i] = getorderadmin.getOrderId();
                    date[i] = getorderadmin.getDate();
                    OrderPrice[i] = getorderadmin.getOrderPrice();
                    OrderStatus[i] = getorderadmin.getOrderStatus();
                    PaymentStatus[i] = getorderadmin.getPaymentStatus();
                    i++;
                }
                    GetOrderAdminAdapter goaa = new GetOrderAdminAdapter(getApplicationContext(),CustomerName,OrderId,date,OrderPrice,OrderStatus,PaymentStatus);
                    voadmin.setAdapter(goaa);
                //Toast.makeText(getApplicationContext(),"Yeah",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<OrderCustomer>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
