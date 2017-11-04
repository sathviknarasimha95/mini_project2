package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sathvik.android_test.R;

/**
 * Created by sathvik on 11/4/2017.
 */

public class GetOrderDetcAdapter extends ArrayAdapter<String> {
    private String[] ProductNames;
    private int[] ProductNos;
    private String[] UnitPrices;
    private String OrderPrice;
    private Context context;

    public GetOrderDetcAdapter(Context context,String[] ProductName,int[] ProductNo,String[] UnitPrice,String OrderPrice)
    {
        super(context, R.layout.listorderdetails,ProductName);
        this.context = context;
        this.ProductNames = ProductName;
        this.ProductNos = ProductNo;
        this.UnitPrices = UnitPrice;
        this.OrderPrice = OrderPrice;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listorderdetails, parent, false);
        TextView ProductName = (TextView) rowView.findViewById(R.id.details_item_name);
        TextView ProductNo = (TextView) rowView.findViewById(R.id.details_item_no);
        TextView UnitPrice = (TextView) rowView.findViewById(R.id.details_item_price);
        //Log.i("Dsatasat=",CustomerId[1]+"");

        ProductName.setText(ProductNames[position]);
        //date.setText(Date[position].toString());

        //String dates = Date[position].toString().substring(0,10);
        //Toast.makeText(context,dates,Toast.LENGTH_LONG).show();
        ProductNo.setText("X"+ProductNos[position]+"");
        UnitPrice.setText(UnitPrices[position]+"");
        return rowView;
    }
}
