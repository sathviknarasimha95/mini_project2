package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.PaymentInfo_Adapter;
import com.example.sathvik.android_test.api.PaymentDetails;
import com.example.sathvik.android_test.models.PaymentDet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Payment_list extends AppCompatActivity {
    String FileName = "Login_fine";
    String CustomerId;
    String[] OrderId;
    String[] OrderStatus;
    String[] OrderPrice;
    String[] Date;
    String[] PaymentStatus;
    ListView plp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentlist);
        plp = (ListView)findViewById(R.id.payment_list);
        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        CustomerId = LoginSharedPref.getString("CustomerId",defaultValue);
        Bundle bundle = getIntent().getExtras();
        String status = bundle.getString("status");
        //int CustomerId = Integer.parseInt(LoginSharedPref.getString("CustomerId",defaultValue));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PaymentDetails paymentDetails = retrofit.create(PaymentDetails.class);
        Call<List<PaymentDet>> payment = paymentDetails.getPaymentInfo(CustomerId,status);
        payment.enqueue(new Callback<List<PaymentDet>>() {
            @Override
            public void onResponse(Call<List<PaymentDet>> call, Response<List<PaymentDet>> response) {
                //Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                OrderId = new String[response.body().size()];
                OrderStatus = new String[response.body().size()];
                OrderPrice = new String[response.body().size()];
                Date = new String[response.body().size()];
                PaymentStatus = new String[response.body().size()];
                int i=0;
                for(PaymentDet pay : response.body())
                {
                    OrderId[i] = pay.getOrderId();
                    OrderStatus[i] = pay.getOrderStatus();
                    OrderPrice[i] = pay.getOrderPrice();
                    Date[i] = pay.getDate();
                    PaymentStatus[i] = pay.getPaymentStatus();
                    i++;
                }
                PaymentInfo_Adapter pia = new PaymentInfo_Adapter(getApplicationContext(),OrderId,OrderPrice,OrderStatus,Date,PaymentStatus);
                plp.setAdapter(pia);
            }

            @Override
            public void onFailure(Call<List<PaymentDet>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
