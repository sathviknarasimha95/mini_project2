package com.example.sathvik.android_test.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.models.AdminMenu;
import com.example.sathvik.android_test.ui.Order;
import com.example.sathvik.android_test.ui.ViewOrderAdmin;

import java.util.List;

/**
 * Created by sathvik on 10/31/2017.
 */

public class AdminMenuAdapter extends RecyclerView.Adapter<AdminMenuAdapter.MyViewHolder>{
    private List<AdminMenu> ServiceList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        }
    }

    public AdminMenuAdapter(Context context, List<AdminMenu> albumList) {
        this.context = context;
        this.ServiceList = albumList;
    }

     public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdminMenuAdapter.MyViewHolder holder, int position) {
        final AdminMenu album = ServiceList.get(position);
        holder.title.setText(album.getName());

        // loading album cover using Glide library
        Glide.with(context).load(album.getThumbnail()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context,"You clicked"+album.getName(),Toast.LENGTH_SHORT).show();
                intentRedirect(album.getName());
            }

        });



    }
    public void intentRedirect(String name)
    {
        if(name.equals("Ongoing Orders"))
        {
            Intent gotorder = new Intent(context,ViewOrderAdmin.class);
            context.startActivity(gotorder);
        }
        else
        {
            Toast.makeText(context,"You Clicked "+name,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return ServiceList.size();
    }
}

