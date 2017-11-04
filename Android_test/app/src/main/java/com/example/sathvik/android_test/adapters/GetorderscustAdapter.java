package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;

/**
 * Created by sathvik on 11/3/2017.
 */

public class GetorderscustAdapter extends ArrayAdapter<String> {

    private String[] OrderIds;
    private String[] Date;
    private String[] OrderStatus;
    private float[] Price;
    private Context context;

    public GetorderscustAdapter(Context context, String[] OrderIds, String[] date, float[] price,String[] OrderStatus) {
        super(context, R.layout.listgetorders, date);
        this.context = context;
        this.OrderIds = OrderIds;
        this.Date = date;
        this.Price = price;
        this.OrderStatus = OrderStatus;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listgetorders, parent, false);
        TextView customerid = (TextView) rowView.findViewById(R.id.orderCustomerid);
        TextView date = (TextView) rowView.findViewById(R.id.getDate);
        TextView price = (TextView) rowView.findViewById(R.id.orderprice);
        TextView status = (TextView) rowView.findViewById(R.id.orderstatusCustomer);
        //Log.i("Dsatasat=",CustomerId[1]+"");

        customerid.setText(OrderIds[position]);
        //date.setText(Date[position].toString());

        String dates = Date[position].toString().substring(0,10);
        //Toast.makeText(context,dates,Toast.LENGTH_LONG).show();
        date.setText(dates);
        price.setText(Price[position]+"");
        status.setText(OrderStatus[position]);
        return rowView;
    }
}
