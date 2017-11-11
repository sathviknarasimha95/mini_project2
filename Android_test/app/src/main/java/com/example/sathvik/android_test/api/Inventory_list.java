package com.example.sathvik.android_test.api;

/**
 * Created by Sathvik on 7/22/2017.
 */
import com.example.sathvik.android_test.models.Inventory;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Inventory_list {
    @Headers("Content-Type: application/json")
    @GET("inventory")
    Call<List<Inventory>> getData();
    @FormUrlEncoded
    @POST("/inventory")
    Call<List<Inventory>> get_TypeData(@Field("ProductType") String ptype);


}
