package com.example.sathvik.android_test.api;

import com.example.sathvik.android_test.models.LoginInfo;
import com.example.sathvik.android_test.models.OrderCustomer;
import com.example.sathvik.android_test.models.Pending_customers;
import com.example.sathvik.android_test.models.Registeration;
import com.example.sathvik.android_test.models.TokenInfo;
import com.example.sathvik.android_test.models.Updatepending;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Sathvik on 6/29/2017.
 */

public interface UserLogin {


    @FormUrlEncoded
    @POST("/login")
    Call<LoginInfo> sendLogin(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("/updatetoken")
    Call<TokenInfo> updateToken(
            @Field("token") String token,
            @Field("CustomerId") String CustomerId
    );

    @FormUrlEncoded
    @POST("/createusers")
    Call<Registeration> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("address") String address,
            @Field("phno") String phno,
            @Field("dob") String dob
    );

    @Headers("Content-Type: application/json")
    @GET("/getpendingusers")
    Call<List<Pending_customers>> getPending();

    @FormUrlEncoded
    @POST("/confirmusers")
    Call<Updatepending> confirmuser(
            @Field("CustomerId") String CustomerId,
            @Field("email") String email,
            @Field("status") String status);


}
