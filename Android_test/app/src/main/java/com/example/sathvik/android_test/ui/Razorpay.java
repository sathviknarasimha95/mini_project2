package com.example.sathvik.android_test.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.api.PaymentDetails;
import com.example.sathvik.android_test.models.PaymentDet;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Razorpay extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = Razorpay.class.getSimpleName();
    String FileName = "Login_fine";
    String OrderID;
    String OrderPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);
        Bundle bundle = getIntent().getExtras();
         OrderID = bundle.getString("OrderId");
         OrderPrice = bundle.getString("OrderPrice");

        TextView orderid = (TextView)findViewById(R.id.OrderIdrazor);
        TextView Price = (TextView)findViewById(R.id.OrderPricerazor);

        orderid.setText("Order #"+OrderID);
        Price.setText("INR "+OrderPrice);

        Button paybtn = (Button)findViewById(R.id.btn_pay);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Sanjanaa Pharma");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9876543210");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            String email = get_sharedpref("email");
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            Date cDate = new Date();
            String fDate = new SimpleDateFormat("yyyy/MM/dd").format(cDate);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getApplicationContext().getString(R.string.uri))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            PaymentDetails paymentDetails = retrofit.create(PaymentDetails.class);
            Call<PaymentDet> razorpay = paymentDetails.updateRazorpay(OrderID,fDate,razorpayPaymentID,email);
            razorpay.enqueue(new Callback<PaymentDet>() {
                @Override
                public void onResponse(Call<PaymentDet> call, Response<PaymentDet> response) {
                    Toast.makeText(getApplicationContext(), "Payment Success ThankYou", Toast.LENGTH_SHORT).show();
                    Intent gotohome = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(gotohome);
                }

                @Override
                public void onFailure(Call<PaymentDet> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
    public String get_sharedpref(String data)
    {
        SharedPreferences SharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        //Toast.makeText(getApplicationContext(),SharedPref.getString(data,defaultValue),Toast.LENGTH_LONG).show();
        return SharedPref.getString(data,defaultValue);

    }
}
