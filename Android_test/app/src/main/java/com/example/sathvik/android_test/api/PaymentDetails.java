package com.example.sathvik.android_test.api;

import com.example.sathvik.android_test.models.PaymentDet;
import com.example.sathvik.android_test.models.PaymentHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sathvik on 11/12/2017.
 */

public interface PaymentDetails {
    @FormUrlEncoded
    @POST("/getpaymentdetails")
    Call<List<PaymentDet>> getPaymentInfo(@Field("CustomerId") String CustomerId,
                                          @Field("status") String Status);
    @FormUrlEncoded
    @POST("/getpaymenthistory")
    Call<List<PaymentHistory>> getPaymentHistory(@Field("CustomerId") String CustomerId,@Field("Type") String type);

    @FormUrlEncoded
    @POST("/updatepaymentrazor")
    Call<PaymentDet> updateRazorpay(@Field("OrderId") String OrderId,@Field("date") String date,@Field("transid") String TransId,@Field("email") String email);

}
