package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    private String[] PaymentStatus;
    Context context;

    public GetOrderAdminAdapter(Context context,String[] CustomerName,String[] OrderId,String[] Date,float[] OrderPrice,String[] OrderStatus,String[] PaymentStatus)
    {
        super(context, R.layout.adminlist,CustomerName);
        this.context = context;
        this.Customername = CustomerName;
        this.Orderid = OrderId;
        this.date = Date;
        this.Orderprice = OrderPrice;
        this.Orderstatus = OrderStatus;
        this.PaymentStatus = PaymentStatus;
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
        TextView status = (TextView)rowView.findViewById(R.id.admin_order_status);
        ImageView img = (ImageView)rowView.findViewById(R.id.admin_layout_image);
        TextView pay = (TextView)rowView.findViewById(R.id.payment);

        cuname.setText(Customername[position]);
        String dated = date[position].substring(0,10);
        dates.setText(dated);
        orderid.setText(":"+Orderid[position]);
        price.setText(Orderprice[position]+"");
        status.setText(Orderstatus[position]);
        pay.setText(PaymentStatus[position]);
        if(Orderstatus[position].equals("Pending"))
        {
            img.setImageResource(R.drawable.pending);
        }
        else if(Orderstatus[position].equals("Ongoing"))
        {
            img.setImageResource(R.drawable.ongoing);
        }
        else if(Orderstatus[position].equals("Completed"))
        {
            img.setImageResource(R.drawable.completed);
        }
        else if(Orderstatus[position].equals("Cancelled"))
        {
            img.setImageResource(R.drawable.cancelled);
        }

        return rowView;
    }
}
