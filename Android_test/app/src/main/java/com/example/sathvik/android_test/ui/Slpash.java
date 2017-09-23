package com.example.sathvik.android_test.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.sathvik.android_test.R;

public class Slpash extends Activity {
    String FileName = "Login_fine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slpash);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        iv.startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
                String defaultValue = "DefaultName";
                String username = LoginSharedPref.getString("Username",defaultValue);
                String Token = LoginSharedPref.getString("Token",defaultValue);
                Log.i("Login","on splash   "+username);
                Log.i("Login","on splash   "+Token);
                if(username.equals("DefaultName") && Token.equals("DefaultName")) {
                    // create Intent for next activity and call startActivity with it
                     Intent Login = new Intent(getApplicationContext(),Login.class);
                     startActivity(Login);
                }
                else
                {
                    Intent Home = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(Home);
                }
            }
        }, 3000);
    }
    public void onImageClick(View v)
    {
        ImageView image =(ImageView) findViewById(R.id.imageView);
        //Toast.makeText(this,"Image was Clicked",Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(getApplicationContext(),Login.class);
        //startActivity(intent);

    }
}
