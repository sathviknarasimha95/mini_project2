package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.GetOrderDetcAdapter;
import com.example.sathvik.android_test.api.GetOrderAdmin;
import com.example.sathvik.android_test.api.GetOrderDetails;
import com.example.sathvik.android_test.models.OrderCustomerDetails;
import com.example.sathvik.android_test.models.OrderStatus;

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
    String OrderId;
    String FileName = "Login_fine";
    String Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_customer);
        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        String role = LoginSharedPref.getString("Role",defaultValue);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.admin_buttons);
        if(role.equals("user")) {
            rl.setVisibility(View.INVISIBLE);
        }
        else if(role.equals("admin"))
        {
            rl.setVisibility(View.VISIBLE);
        }
        Bundle bundle = getIntent().getExtras();
        OrderId = bundle.getString("OrderId");
        Status = bundle.getString("Status");

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
                Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
            }
        });
        if(role.equals("admin")) {
            if (Status.equals("Completed")) {
                rl.setVisibility(View.INVISIBLE);
            }
            Button status = (Button) findViewById(R.id.accept_order);
            String d = status.getText().toString();
            Button cancel = (Button) findViewById(R.id.cancel_order);

            if (Status.equals("Pending")) {
                status.setText("Accept Order");
            } else if (Status.equals("Ongoing")) {
                status.setText("Complete Order");
            }
            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (status.getText().equals("Accept Order")) {
                        accept_order();
                    } else if (status.getText().equals("Complete Order")) {
                        complete_order();
                    }

                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancel_order();
                }
            });
        }
    }
    public void accept_order()
    {
        AlertDialog.Builder updateorder = new AlertDialog.Builder(OrderDetailCustomer.this);
        updateorder.setTitle(" Accept Order");
        updateorder.setMessage("Are you Sure?");
        updateorder.setCancelable(false);
        String YesButtonText = "Yes";
        String NoButtonText = "No";
        String status ="Ongoing";

        updateorder.setPositiveButton(YesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                update_order(status);
            }
        });
        updateorder.setNegativeButton(NoButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        updateorder.show();
    }
    public void complete_order()
    {
        AlertDialog.Builder updateorder = new AlertDialog.Builder(OrderDetailCustomer.this);
        updateorder.setTitle(" Complete Order");
        updateorder.setMessage("Are you Sure?");
        updateorder.setCancelable(false);
        String YesButtonText = "Yes";
        String NoButtonText = "No";
        String status ="Completed";

        updateorder.setPositiveButton(YesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                update_order(status);
            }
        });
        updateorder.setNegativeButton(NoButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        updateorder.show();
    }
    public void update_order(String status)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetOrderAdmin getOrderAdmin = retrofit.create(GetOrderAdmin.class);
        Call<OrderStatus>  updateOrderAdmin = getOrderAdmin.updateOrderAdmin(OrderId,status);
        updateOrderAdmin.enqueue(new Callback<OrderStatus>() {
            @Override
            public void onResponse(Call<OrderStatus> call, Response<OrderStatus> response) {
                Intent gotomain = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(gotomain);
            }

            @Override
            public void onFailure(Call<OrderStatus> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void cancel_order()
    {
        AlertDialog.Builder updateorder = new AlertDialog.Builder(OrderDetailCustomer.this);
        updateorder.setTitle("Cancel Order");
        updateorder.setMessage("Are you Sure?");
        updateorder.setCancelable(false);
        String YesButtonText = "Yes";
        String NoButtonText = "No";
        String status ="Cancelled";

        updateorder.setPositiveButton(YesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                update_order(status);
            }
        });
        updateorder.setNegativeButton(NoButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        updateorder.show();
    }
}
