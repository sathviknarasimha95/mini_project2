package com.example.sathvik.android_test.api;

import com.example.sathvik.android_test.models.PlaceOrder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sathvik on 11/2/2017.
 */

public interface OrderProducts {
    @FormUrlEncoded
    @POST("/placeorder")
    Call<PlaceOrder> placeOrder(@Field("OrderId") String OrderId,
                                   @Field("CustomerId") int CustomerId,
                                   @Field("Date") String Dates,
                                   @Field("OrderPrice") float total,
                                   @Field("itemName") String[] itemName,
                                   @Field("itemPrice") String[] itemPrice,
                                   @Field("ProductId") String[] itemId,
                                   @Field("ProductNo") String[] itemNo
                                   );

}
