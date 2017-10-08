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
import android.widget.TextView;

import com.example.sathvik.android_test.R;

/**
 * Created by sathvik on 10/8/2017.
 */

public class Cartadapter extends ArrayAdapter<String> {
        private String[] cart_item_name;
        private String[] cart_item_no;
        private String[] cart_item_price;
        private Context context;
        float total = 0;
        TextView totals;

    public Cartadapter(Context context,String[] cart_item_name,String[] cart_item_no,String[] cart_item_price,float total,TextView totals)
    {
        super(context, R.layout.cart_list, cart_item_name);
        this.context = context;
        this.cart_item_name = cart_item_name;
        this.cart_item_no = cart_item_no;
        this.cart_item_price = cart_item_price;
        this.total = total;
        this.totals = totals;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cart_list, parent, false);
        View textview = inflater.inflate(R.layout.activity_cart,parent,false);
        TextView cart_name = (TextView)rowView.findViewById(R.id.cart_item_name);
        TextView cart_no = (TextView)rowView.findViewById(R.id.cart_item_no);
        TextView cart_price = (TextView)rowView.findViewById(R.id.cart_item_price);
        Button placeorder = (Button)rowView.findViewById(R.id.place_order);
        cart_name.setText(cart_item_name[position]);
        cart_price.setText(cart_item_price[position]);
        cart_no.setText("X "+cart_item_no[position]);
        totals.setText("Total="+total);

        return  rowView;
    }
}
