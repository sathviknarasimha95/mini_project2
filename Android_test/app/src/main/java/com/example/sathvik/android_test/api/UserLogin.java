package com.example.sathvik.android_test.api;

import com.example.sathvik.android_test.models.LoginInfo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}
