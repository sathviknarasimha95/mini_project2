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

public class GetOrderAdminAdapter extends ArrayAdapter<String> {
    private String[] Customername;
    private String[] Orderid;
    private String[] date;
    private float[] Orderprice;
    private String[] Orderstatus;
    Context context;

    public GetOrderAdminAdapter(Context context,String[] CustomerName,String[] OrderId,String[] Date,float[] OrderPrice,String[] OrderStatus)
    {
        super(context, R.layout.adminlist,CustomerName);
        this.context = context;
        this.Customername = CustomerName;
        this.Orderid = OrderId;
        this.date = Date;
        this.Orderprice = OrderPrice;
        this.Orderstatus = OrderStatus;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adminlist, parent, false);
        TextView cuname = (TextView)rowView.findViewById(R.id.admin_order_name);
        TextView dates = (TextView)rowView.findViewById(R.id.admin_order_date);
        TextView orderid = (TextView)rowView.findViewById(R.id.admin_order_id);
        TextView price = (TextView)rowView.findViewById(R.id.admin_order_price);

        cuname.setText(Customername[position]);
        dates.setText(date[position]);
        orderid.setText(Orderid[position]);
        return rowView;
    }
}
