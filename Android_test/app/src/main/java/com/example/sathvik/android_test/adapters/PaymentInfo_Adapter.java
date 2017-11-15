package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.ui.Razorpay;

/**
 * Created by sathvik on 11/12/2017.
 */

public class PaymentInfo_Adapter extends ArrayAdapter<String>{
    String[] OrderId;
    String[] OrderPrice;
    String[] OrderStatus;
    String[] Date;
    String[] PaymentStatus;
    Context context;
    String TAG = "payment gateway";
    int pos;
    public PaymentInfo_Adapter(Context context,String[] OrderId,String[] OrderPrice,String[] OrderStatus,String[] Date,String[] PaymentStatus)
    {
        super(context, R.layout.payment_list, OrderId);
        this.context = context;
        this.OrderId = OrderId;
        this.OrderPrice = OrderPrice;
        this.OrderStatus = OrderStatus;
        this.Date = Date;
        this.PaymentStatus = PaymentStatus;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //pos = position;
        View rowView = inflater.inflate(R.layout.payment_list, parent, false);
        TextView payment_date = (TextView)rowView.findViewById(R.id.customer_name);
        TextView payment_price = (TextView)rowView.findViewById(R.id.customer_phno);
        TextView payment_orderid = (TextView)rowView.findViewById(R.id.Customerid_pen);
        TextView payment_ordestatus = (TextView)rowView.findViewById(R.id.payment_orderstatusCustomer);
        String tdate = Date[position].substring(1,10);
        payment_date.setText(tdate);
        payment_price.setText(OrderPrice[position]);
        payment_orderid.setText(OrderId[position]);
        payment_ordestatus.setText(OrderStatus[position]);
        if(PaymentStatus[position].equals("Paid")) {
            Button pay = (Button) rowView.findViewById(R.id.pay);
            pay.setVisibility(View.INVISIBLE);
        }
        Button pay = (Button) rowView.findViewById(R.id.pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,OrderPrice[position]+"",Toast.LENGTH_SHORT).show();
                Intent gotorazor = new Intent(context,Razorpay.class);
                gotorazor.putExtra("OrderId",OrderId[position]);
                gotorazor.putExtra("OrderPrice",OrderPrice[position]);
                context.startActivity(gotorazor);
            }
        });
        return rowView;
    }
}
