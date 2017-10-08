package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.sathvik.android_test.R;

/**
 * Created by sathvik on 10/4/2017.
 */

public class BaseActivity extends AppCompatActivity {
    String FileName = "Login_fine";
    MaterialDialog progress;
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
            if(id == R.id.cart_button)
            {
                Intent cart = new Intent(getApplicationContext(),Cart.class);
                startActivity(cart);
            }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    public void logout()
    {
        AlertDialog.Builder logoutalert = new AlertDialog.Builder(BaseActivity.this);
        logoutalert.setTitle("Logout");
        logoutalert.setMessage("Are you Sure?");
        logoutalert.setCancelable(false);
        String YesButtonText = "Yes";
        String NoButtonText = "No";
        logoutalert.setPositiveButton(YesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
                LoginSharedPref.edit().remove("Username").commit();
                LoginSharedPref.edit().remove("Token").commit();
                Intent gotoslogin = new Intent(getApplicationContext(),Login.class);
                startActivity(gotoslogin);
            }
        });
        logoutalert.setNegativeButton(NoButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        logoutalert.show();
    }
}
