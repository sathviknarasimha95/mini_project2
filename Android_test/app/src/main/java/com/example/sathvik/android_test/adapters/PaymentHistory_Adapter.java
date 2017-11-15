package com.example.sathvik.android_test.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.models.Payment_historyadp;

/**
 * Created by sathvik on 11/13/2017.
 */

public class PaymentHistory_Adapter  extends ArrayAdapter {
    Context context;

    //private List<Payment_historyadp> paymenthistory = null;
    //private ArrayList<Payment_historyadp> arraylist;
    TextView payment_date;
    TextView payment_price;
    TextView payment_orderid;
    TextView payment_status;
    String[] OrderId;
    String[] Payment_status;
    String[] PaymentDate;
    String[] OrderPrice;
    String[] PaymentType;
    private List<Payment_historyadp> Payment_history = null;
    ArrayList<Payment_historyadp> arraylist;

    //public PaymentHistory_Adapter(Context context,String[] OrderId,String[] Payment_status,String[] PaymentDate,String[] OrderPrice,String[] PaymentType) {
    public PaymentHistory_Adapter(Context context, List<Payment_historyadp> payment_history) {
        super(context, R.layout.paymenthistorylist, payment_history);
        this.context = context;
        this.Payment_history = payment_history;
        this.arraylist = new ArrayList<Payment_historyadp>();
        this.arraylist.addAll(Payment_history);
        /*this.paymenthistory = paymenthistory;

        this.arraylist = new ArrayList<Payment_historyadp>();
        this.arraylist.addAll(paymenthistory);*/
        this.OrderId = OrderId;
        this.Payment_status = Payment_status;
        this.PaymentDate = PaymentDate;
        this.OrderPrice = OrderPrice;
        this.PaymentType = PaymentType;
    }

    @Override
    public int getCount() {
        return Payment_history.size();
    }

    @Override
    public Payment_historyadp getItem(int position) {
        return Payment_history.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

//        Toast.makeText(context,"success",Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.paymenthistorylist, parent, false);
        payment_date = (TextView) rowView.findViewById(R.id.paymenthistory_getDate);
        payment_price = (TextView) rowView.findViewById(R.id.paymenthistory_orderprice);
        payment_orderid = (TextView) rowView.findViewById(R.id.paymenthistory_orderid);
        payment_status = (TextView) rowView.findViewById(R.id.paymenthistory_orderstatus);
        payment_date.setText(Payment_history.get(position).getPaymentDate());
        payment_price.setText(Payment_history.get(position).getOrderPrice());
        payment_orderid.setText(Payment_history.get(position).getOrderId());
        payment_status.setText(Payment_history.get(position).getPaymentStatus());
        return rowView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Toast.makeText(context, charText, Toast.LENGTH_SHORT).show();
        Payment_history.clear();
        if (charText.length() == 0) {
            Payment_history.addAll(arraylist);
        }
        else
        {
            for (Payment_historyadp wp : arraylist)
            {
                if (wp.getOrderId().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    Payment_history.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

