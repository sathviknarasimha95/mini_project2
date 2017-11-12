package com.example.sathvik.android_test.api;

import com.example.sathvik.android_test.models.OrderCustomer;
import com.example.sathvik.android_test.models.OrderStatus;
import com.example.sathvik.android_test.models.Otpgen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sathvik on 11/7/2017.
 */

public interface GetOrderAdmin {
    @FormUrlEncoded
    @POST("/getorderadmin")
    Call<List<OrderCustomer>> getOrderAdmin(
            @Field("OrderStatus") String OrderStatus

    );
    @FormUrlEncoded
    @POST("/updateorderstatus")
    Call<OrderStatus> updateOrderAdmin(
            @Field("OrderId") String OrderId,
            @Field("OrderStatus") String OrderStatus,
            @Field("PaymentStatus") String PaymentStatus,
            @Field("Date") String Date
    );

    @FormUrlEncoded
    @POST("/otpgen")
    Call<Otpgen> otpgen(
            @Field("OrderId") String OrderId
    );
}
