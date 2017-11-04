package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sathvik.android_test.R;

/**
 * Created by sathvik on 10/31/2017.
 */

public class Admin_Adapter extends ArrayAdapter<String> {
    private Context context;
    private String[] titles;

    public Admin_Adapter(Context context,String[] titles)
    {
        super(context, R.layout.adminlist, titles);
        this.context = context;
        this.titles = titles;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adminlist, parent, false);
        TextView admin_title = (TextView) rowView.findViewById(R.id.admin_title);
        admin_title.setText(titles[position]);
        return rowView;
    }
}
