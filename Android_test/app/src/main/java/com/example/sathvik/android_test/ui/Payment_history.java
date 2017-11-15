package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.PaymentHistory_Adapter;
import com.example.sathvik.android_test.api.PaymentDetails;
import com.example.sathvik.android_test.models.PaymentHistory;
import com.example.sathvik.android_test.models.Payment_historyadp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Payment_history extends AppCompatActivity {
    String CustomerId;
    String FileName = "Login_fine";
    ListView ph;
    String[] OrderId;
    String[] OrderPrice;
    String[] PaymentStatus;
    String[] PaymentDate;
    String[] PaymentType;
    PaymentHistory_Adapter adapter;
    //EditText editsearch;
    ArrayList<Payment_historyadp> arraylist = new ArrayList<Payment_historyadp>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        ph = (ListView)findViewById(R.id.payment_history);
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("Type");

        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        CustomerId = LoginSharedPref.getString("CustomerId",defaultValue);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PaymentDetails paymentDetails = retrofit.create(PaymentDetails.class);
        Call<List<PaymentHistory>> paymenthist = paymentDetails.getPaymentHistory(CustomerId,type);

        paymenthist.enqueue(new Callback<List<PaymentHistory>>() {
            @Override
            public void onResponse(Call<List<PaymentHistory>> call, Response<List<PaymentHistory>> response) {
                //Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                OrderId = new String[response.body().size()];
                OrderPrice = new String[response.body().size()];
                PaymentStatus = new String[response.body().size()];
                PaymentDate = new String[response.body().size()];
                PaymentType = new String[response.body().size()];
                int i =0;
                for(PaymentHistory paymentHistory : response.body())
                {
                    OrderId[i] = paymentHistory.getOrderId();
                    OrderPrice[i] = paymentHistory.getOrderPrice();
                    PaymentStatus[i] = paymentHistory.getPaymentStatus();
                    PaymentDate[i] = paymentHistory.getPaymentDate();
                    PaymentType[i] = paymentHistory.getPaymentType();
                    i++;
                }
                for(int j = 0 ; j<response.body().size();j++)
                {
                    Payment_historyadp ph = new Payment_historyadp(OrderId[j], OrderPrice[j],
                            PaymentStatus[j],PaymentDate[j],PaymentType[j]);
                    arraylist.add(ph);
                }
                adapter = new PaymentHistory_Adapter(getApplicationContext(),arraylist);
                ph.setAdapter(adapter);
                //adapter = new PaymentHistory_Adapter(getApplicationContext(),OrderId,PaymentStatus,PaymentDate,OrderPrice,PaymentType);
                //ph.setAdapter(adapter);
                SearchView editsearch = (SearchView)findViewById(R.id.history_search);

                editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        String text = s.toString().toLowerCase(Locale.getDefault());
                        //adapter.filter(text);
                        adapter.filter(text);
                        //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PaymentHistory>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
