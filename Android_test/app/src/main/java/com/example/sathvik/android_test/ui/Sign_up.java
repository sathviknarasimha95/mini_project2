package com.example.sathvik.android_test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sathvik.android_test.R;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button sign = (Button) findViewById(R.id.btnSignUp);
    }

    public void signClick(View v)
    {
        Toast.makeText(this,"button was clicked",Toast.LENGTH_SHORT).show();
    }
}
