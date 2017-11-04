package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.models.SendToCart;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sathvik on 10/8/2017.
 */

public class Cartadapter extends ArrayAdapter<String> {
        private ArrayList<String> cart_item_name = new ArrayList<String>();
        private ArrayList<String> cart_item_no = new ArrayList<String>();
        private ArrayList<String> cart_item_price = new ArrayList<String>();
        private Context context;
        float total = 0;
        TextView totals;
        SendToCart sp;

    public Cartadapter(Context context,ArrayList cart_item_name,ArrayList cart_item_no,ArrayList cart_item_price)
    {
        super(context, R.layout.cart_list, cart_item_name);
        this.context = context;
        this.cart_item_name = cart_item_name;
        this.cart_item_no = cart_item_no;
        this.cart_item_price = cart_item_price;
        //this.total = total;
        sp = SendToCart.getInstance();
        this.total = sp.getTotal();
        this.totals = totals;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cart_list, parent, false);
        View textview = inflater.inflate(R.layout.activity_cart,parent,false);
        TextView cart_name = (TextView)rowView.findViewById(R.id.cart_item_name);
        TextView cart_no = (TextView)rowView.findViewById(R.id.cart_item_no);
        TextView cart_price = (TextView)rowView.findViewById(R.id.cart_item_price);
        Button placeorder = (Button)rowView.findViewById(R.id.place_order);
        cart_name.setText(cart_item_name.get(position));
        cart_price.setText(cart_item_price.get(position));
        cart_no.setText("X "+cart_item_no.get(position));
        //totals.setText("Total="+total);


        /*rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
                SendToCart sc = SendToCart.getInstance();


            }
        });*/

        return  rowView;
    }


}
