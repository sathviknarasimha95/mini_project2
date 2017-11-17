package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.api.UserLogin;
import com.example.sathvik.android_test.models.LoginInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sathvik on 6/29/2017.
 */

public class Login extends AppCompatActivity {
    String FileName = "Login_fine";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_from);
        final EditText email = (EditText) findViewById(R.id.text_email);
        final EditText password = (EditText) findViewById(R.id.text_passwd);
        TextView textView = (TextView) findViewById(R.id.notmember);
        findViewById(R.id.login_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getApplicationContext().getString(R.string.uri))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //Toast.makeText(Login.this,"Button is clicked",Toast.LENGTH_SHORT).show();
                //Toast.makeText(Login.this,email.getText().toString(),Toast.LENGTH_SHORT).show();
                String emails = email.getText().toString();
                String passwd = password.getText().toString();
                UserLogin userLogin = retrofit.create(UserLogin.class);
                Call<LoginInfo> call = userLogin.sendLogin(
                        emails,
                        passwd
                );
                call.enqueue(new Callback<LoginInfo>() {
                    @Override
                    public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                        //Toast.makeText(Login.this,"success",Toast.LENGTH_LONG).show();
                        Log.i("Login", "onResponse: Server Response: " + response.toString());
                        Log.i("Login", "onResponse: received information: " + response.body().toString());
                        Log.i("Login", "came here");
                        LoginInfo data = response.body();
                        String Logininfo = data.getStatus().toString();
                        String Token = data.getToken().toString();
                        String Username = data.getUsername().toString();
                        String role = data.getRole().toString();
                        int CustomerId = data.getCustomerId();
                        Log.i("Login", "info:" + Logininfo);
                        if(Logininfo.equals("login_successfull") && role.equals("user")) {
                            saveLoginDetails(Logininfo,Token,Username,role,CustomerId,emails);
                            //readDetails();
                            Intent mains = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mains);
                        }else if(Logininfo.equals("login_successfull") && role.equals("admin"))
                        {
                            saveLoginDetails(Logininfo,Token,Username,role,CustomerId,emails);
                            //readDetails();
                            Intent mains = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(mains);
                        }
                        else if(Logininfo.equals("No_such_users_found"))
                        {
                            Toast.makeText(Login.this,"Email does not exist",Toast.LENGTH_LONG).show();
                        }
                        else if(Logininfo.equals("password_is_incorrect"))
                        {
                            Toast.makeText(Login.this,"Invalid Password",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginInfo> call, Throwable t) {
                        Toast.makeText(Login.this,"failure",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosignup = new Intent(getApplicationContext(),Sign_up.class);
                startActivity(gotosignup);
            }
        });
            readDetails();
    }

    public void saveLoginDetails(String Status,String Token,String Name,String role,int CustomerId,String email)
    {

        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = LoginSharedPref.edit();
        editor.putString("Login_Status",Status.toString());
        editor.putString("Username",Name.toString());
        editor.putString("Token",Token.toString());
        editor.putString("Role",role.toString());
        editor.putString("CustomerId",CustomerId+"");
        editor.putString("email",email);
        Log.i("CustomerId",email+"");
        editor.commit();
        Log.i("Login","Data written successfuly");
        return;
    }
    public void readDetails()
    {
        SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultName";
        String username = LoginSharedPref.getString("Token",defaultValue);
        String email = LoginSharedPref.getString("email",defaultValue);
        Log.i("Login email","Data read successfuly"+email);
        Log.i("Login","Data read successfuly"+username);
    }
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
        alert.setTitle("Exit");
        alert.setMessage("Are You Sure to Exit?");
        alert.setCancelable(false);
        String yesButtonText = "Yes";
        String NoButtonText = "No";
        alert.setPositiveButton(yesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent exitapp = new Intent(Intent.ACTION_MAIN);
                exitapp.addCategory(Intent.CATEGORY_HOME);
                startActivity(exitapp);
            }
        });
        alert.setNegativeButton(NoButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }
}
