package com.example.sathvik.android_test.api;

import com.example.sathvik.android_test.models.Inventory;
import com.example.sathvik.android_test.models.OrderCustomer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by sathvik on 11/3/2017.
 */

public interface GetOrders {
    @FormUrlEncoded
    @POST("/getorders")
    Call<List<OrderCustomer>> getOrder(
            @Field("CustomerId") int CustomerId

    );

    @Headers("Content-Type: application/json")
    @GET("/getorders")
    Call<List<OrderCustomer>> getData();
}
