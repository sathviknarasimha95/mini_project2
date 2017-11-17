package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.api.UserLogin;
import com.example.sathvik.android_test.models.Updatepending;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    EditText passwordnew;
    EditText passwordnew2;
    String password;
    String password2;
    String FileName = "Login_fine";
    String CustomerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        passwordnew = (EditText)findViewById(R.id.new_password);
        passwordnew2 = (EditText)findViewById(R.id.new_password2);
        Button update = (Button) findViewById(R.id.update_password);
        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        CustomerId = LoginSharedPref.getString("CustomerId",defaultValue);
        String email = get_sharedpref("email");
        Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG).show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = passwordnew.getText().toString();
                password2 = passwordnew2.getText().toString();
                if(password.equals(password2))
                {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getApplicationContext().getString(R.string.uri))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserLogin updatepassword = retrofit.create(UserLogin.class);
                    Call<Updatepending> updatepass = updatepassword.updatepassword(CustomerId,password2,email);
                    updatepass.enqueue(new Callback<Updatepending>() {
                        @Override
                        public void onResponse(Call<Updatepending> call, Response<Updatepending> response) {
                            Toast.makeText(getApplicationContext(),"Password has been updated Successfully",Toast.LENGTH_LONG).show();
                            logout();
                        }

                        @Override
                        public void onFailure(Call<Updatepending> call, Throwable t) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Passwords does not match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void logout()
    {
        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        LoginSharedPref.edit().remove("Username").commit();
        LoginSharedPref.edit().remove("Token").commit();
        LoginSharedPref.edit().remove("role").commit();
        LoginSharedPref.edit().remove("CustomerId").commit();
        Intent gotoslogin = new Intent(getApplicationContext(),Login.class);
        startActivity(gotoslogin);
    }
    public String get_sharedpref(String data)
    {
        SharedPreferences SharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        return SharedPref.getString(data,defaultValue);

    }
}
