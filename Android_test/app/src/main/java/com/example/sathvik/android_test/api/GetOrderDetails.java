package com.example.sathvik.android_test.api;

import com.example.sathvik.android_test.models.OrderCustomerDetails;
import com.example.sathvik.android_test.models.Otpgen;
import com.example.sathvik.android_test.models.Updatepending;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sathvik on 11/4/2017.
 */

public interface GetOrderDetails {
    @FormUrlEncoded
    @POST("/getorderdetails")
    Call<List<OrderCustomerDetails>> getOrderDetails(
            @Field("OrderId") String OrderId

    );
    @FormUrlEncoded
    @POST("/canceluser")
    Call<Updatepending> canceluser(
            @Field("OrderId") String OrderId

    );


}
