package com.example.sathvik.android_test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.Pendingusersadapter;
import com.example.sathvik.android_test.api.UserLogin;
import com.example.sathvik.android_test.models.Pending_customers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pendingcustomer extends AppCompatActivity {
    String[] CustomerId;
    String[] CustomerName;
    String[] CustomerAdd;
    String[] email;
    String[] CustomerMob;
    ListView pu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingcustomer);
        pu = (ListView)findViewById(R.id.pending_customer_list);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserLogin getpendingusers = retrofit.create(UserLogin.class);
        Call<List<Pending_customers>> pendingusers = getpendingusers.getPending();
        pendingusers.enqueue(new Callback<List<Pending_customers>>() {
            @Override
            public void onResponse(Call<List<Pending_customers>> call, Response<List<Pending_customers>> response) {
                //Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                CustomerId = new String[response.body().size()];
                CustomerName = new String[response.body().size()];
                CustomerAdd = new String[response.body().size()];
                email = new String[response.body().size()];
                CustomerMob = new String[response.body().size()];
                int i = 0;
                for(Pending_customers p : response.body())
                {
                    CustomerId[i] = p.getCustomerId();
                    CustomerName[i] = p.getCustomerName();
                    CustomerAdd[i] = p.getCustomerAdd();
                    email[i] = p.getEmail();
                    CustomerMob[i] = p.getCustomerMob();
                    i++;
                }
                Pendingusersadapter pua = new Pendingusersadapter(pendingcustomer.this,CustomerId,CustomerName,CustomerAdd,email,CustomerMob);
                pu.setAdapter(pua);

            }

            @Override
            public void onFailure(Call<List<Pending_customers>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Some thing Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
