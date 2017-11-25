package com.example.sathvik.android_test.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.api.UserLogin;
import com.example.sathvik.android_test.models.Registeration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class Sign_up extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText phno;
    EditText dob;
    EditText address;
    String cname;
    String cemail;
    String cphno;
    String cdob;
    String caddress;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        name = (EditText)findViewById(R.id.txtname);
        email = (EditText)findViewById(R.id.txtemail);
        phno = (EditText)findViewById(R.id.txtmobileno);
        dob = (EditText)findViewById(R.id.txtdob);
        address = (EditText)findViewById(R.id.txtaddress);
        awesomeValidation.addValidation(this, R.id.txtname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.err_name);
        awesomeValidation.addValidation(this, R.id.txtemail, Patterns.EMAIL_ADDRESS, R.string.email_error);
        awesomeValidation.addValidation(this, R.id.txtmobileno, "^[2-9]{2}[0-9]{8}$", R.string.mob_error);
        //awesomeValidation.addValidation(this, R.id.txtaddress, "[a-z]", R.string.mob_error);

        Button sign = (Button) findViewById(R.id.btnSignUp);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    cname = name.getText().toString();
                    cemail = email.getText().toString();
                    cphno = phno.getText().toString();
                    cdob = dob.getText().toString();
                    caddress = address.getText().toString();
                    Toast.makeText(getApplicationContext(), cname, Toast.LENGTH_SHORT).show();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getApplicationContext().getString(R.string.uri))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserLogin createUsers = retrofit.create(UserLogin.class);
                    Call<Registeration> registerationCall = createUsers.createUser(cname, cemail, caddress, cphno, cdob);
                    registerationCall.enqueue(new Callback<Registeration>() {
                        @Override
                        public void onResponse(Call<Registeration> call, Response<Registeration> response) {

                            Toast.makeText(getApplicationContext(), "Registration success Please Wait for Admin Approval", Toast.LENGTH_LONG).show();
                            Intent gotologin = new Intent(getApplicationContext(), Login.class);
                            startActivity(gotologin);

                        }

                        @Override
                        public void onFailure(Call<Registeration> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
