package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.api.UserLogin;
import com.example.sathvik.android_test.models.Updatepending;
import com.example.sathvik.android_test.ui.Cart;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sathvik on 11/15/2017.
 */

public class Pendingusersadapter extends ArrayAdapter<String>{
    Context context;
    String[] CustomerId;
    String[] CustomerName;
    String[] CustomerAdd;
    String[] email;
    String[] CustomerMob;

    public Pendingusersadapter(Context context, String[] customerId, String[] customerName, String[] customerAdd, String[] email, String[] customerMob) {
        super(context, R.layout.pending_customer_list,customerId);
        this.context = context;
        CustomerId = customerId;
        CustomerName = customerName;
        CustomerAdd = customerAdd;
        this.email = email;
        CustomerMob = customerMob;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //pos = position;
        View rowView = inflater.inflate(R.layout.pending_customer_list, parent, false);
        TextView name  = (TextView)rowView.findViewById(R.id.customer_name);
        TextView phno = (TextView)rowView.findViewById(R.id.customer_phno);
        TextView cid = (TextView)rowView.findViewById(R.id.Customerid_pen);
        TextView add = (TextView)rowView.findViewById(R.id.customer_address);

        name.setText(CustomerName[position]);
        phno.setText(CustomerMob[position]);
        cid.setText(CustomerId[position]);
        add.setText(CustomerAdd[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu options = new PopupMenu(context,view);

                options.getMenuInflater().inflate(R.menu.options_in_cart,options.getMenu());
                options.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String todo = item.getTitle().toString();
                        if(todo.equals("add"))
                        {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(context.getString(R.string.uri))
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            UserLogin apporve = retrofit.create(UserLogin.class);
                            Call<Updatepending> updateapporve = apporve.confirmuser(CustomerId[position],email[position],"Approved");
                            updateapporve.enqueue(new Callback<Updatepending>() {
                                @Override
                                public void onResponse(Call<Updatepending> call, Response<Updatepending> response) {
                                    Toast.makeText(context,"Customer has Been Approved",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<Updatepending> call, Throwable t) {
                                    Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else if(todo.equals("remove"))
                        {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(context.getString(R.string.uri))
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            UserLogin apporve = retrofit.create(UserLogin.class);
                            Call<Updatepending> updateapporve = apporve.confirmuser(CustomerId[position],email[position],"Rejected");
                            updateapporve.enqueue(new Callback<Updatepending>() {
                                @Override
                                public void onResponse(Call<Updatepending> call, Response<Updatepending> response) {
                                    Toast.makeText(context,"Customer has Been Removed",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<Updatepending> call, Throwable t) {
                                    Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        return true;
                    }
                });
                options.show();
            }
        });

        return rowView;
    }


}
