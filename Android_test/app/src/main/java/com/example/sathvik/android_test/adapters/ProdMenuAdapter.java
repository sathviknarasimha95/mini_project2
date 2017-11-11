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
import com.example.sathvik.android_test.models.ProdMenu;
import com.example.sathvik.android_test.ui.Order;

import java.util.List;

/**
 * Created by sathvik on 9/22/2017.
 */

public class ProdMenuAdapter extends RecyclerView.Adapter<ProdMenuAdapter.MyViewHolder>{

    //private Context mContext;
    private List<ProdMenu> albumList;
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

    public ProdMenuAdapter(Context context, List<ProdMenu> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ProdMenu album = albumList.get(position);
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
        if(name.equals("All"))
        {
           Intent gotorder = new Intent(context,Order.class);
            gotorder.putExtra("ProductType", "All");
           context.startActivity(gotorder);
        }
        else if(name.equals("Tablets"))
        {
            //Toast.makeText(context,"You Clicked "+name,Toast.LENGTH_LONG).show();
            Intent gotorder = new Intent(context,Order.class);
            gotorder.putExtra("ProductType", "Tab");
            context.startActivity(gotorder);
        }
        else if(name.equals("Syrups"))
        {
            Intent gotorder = new Intent(context,Order.class);
            gotorder.putExtra("ProductType", "Syp");
            context.startActivity(gotorder);
        }
        else if(name.equals("Injuction"))
        {
            Intent gotorder = new Intent(context,Order.class);
            gotorder.putExtra("ProductType", "Inj");
            context.startActivity(gotorder);
        }
        else if(name.equals("Equipments"))
        {
            Intent gotorder = new Intent(context,Order.class);
            gotorder.putExtra("ProductType", "Equ");
            context.startActivity(gotorder);
        }

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
