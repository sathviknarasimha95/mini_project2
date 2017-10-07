package com.example.sathvik.android_test.adapters;

import android.app.Application;
import android.content.Context;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.example.sathvik.android_test.models.SendToCart;
import com.shawnlin.numberpicker.NumberPicker;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.sathvik.android_test.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by sathvik on 10/2/2017.
 */

public class Orderadapter extends ArrayAdapter<String> {
    private Context context;
    private String[] order_items;
    private String[] item_type;
    private String[] prod_company;
    private String[] item_price;
    private ArrayList<HashMap<String,String>> orderList;
    private HashMap<String,String> map;
    private static int i;

    public Orderadapter(Context context, String[] order_items,String[] item_type,String[] prod_company,String[] item_price) {
        super(context, R.layout.listorder, order_items);
        this.context = context;
        this.order_items = order_items;
        this.item_type = item_type;
        this.prod_company = prod_company;
        this.item_price = item_price;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listorder, parent, false);
        TextView item_name = (TextView) rowView.findViewById(R.id.list_order);
        TextView order_type = (TextView) rowView.findViewById(R.id.order_type);
        TextView order_company = (TextView) rowView.findViewById(R.id.order_company);
        final TextView order_price = (TextView) rowView.findViewById(R.id.order_price);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.order_image);
        Button addtocart = (Button)rowView.findViewById(R.id.addtocart);
        item_name.setText(order_items[position]);
        order_type.setText(item_type[position]);
        order_company.setText(prod_company[position]);
        order_price.setText(item_price[position]+" Rs");
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dialog(view,order_items,position,item_price);
                //Toast.makeText(context,order_items[position],Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
    public void show_dialog(View view, final String[] order_items, final int position, String[] item_price)
    {
        boolean wrapInScrollView = true;

        MaterialDialog md = new MaterialDialog.Builder(view.getRootView().getContext())
                .title("Add to Cart")
                .positiveText("Add")
                .customView(R.layout.number_picker, wrapInScrollView)
                .negativeText("Cancel")
                .show();
        MaterialDialog.Builder builder = new MaterialDialog.Builder(view.getRootView().getContext());
        View vie = md.getCustomView();
        //number picker
        final NumberPicker numberPicker = (NumberPicker)vie.findViewById(R.id.number_picker);
        final TextView item_dialog = (TextView)vie.findViewById(R.id.list_order_dialog);
        final TextView price_dialog = (TextView)vie.findViewById(R.id.order_price_dialog);
        item_dialog.setText(order_items[position]);
        price_dialog.setText(item_price[position]);
        final Double temp = Double.parseDouble(item_price[position]);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                Double res = temp*numberPicker.getValue();
                price_dialog.setText(""+res);
            }
        });
        View positive = md.getActionButton(DialogAction.POSITIVE);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderList = new ArrayList<HashMap<String, String>>();
                map = new HashMap<String, String>();
                //Toast.makeText(context,item_dialog.getText().toString(),Toast.LENGTH_LONG).show();
                map.put("ItemName",item_dialog.getText().toString());
                map.put("Price",price_dialog.getText().toString());
                map.put("No",numberPicker.getValue()+"");
                map.put("Type",item_type[position]);
                map.put("ID",(i++)+"");
                //Toast.makeText(context,i+"",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void addTocart()
    {
        SendToCart sendToCart = SendToCart.getInstance();
        sendToCart.setOrderList(orderList);

    }
}

