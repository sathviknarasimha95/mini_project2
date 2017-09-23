package com.example.sathvik.android_test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.api.Inventory_list;
import com.example.sathvik.android_test.models.Inventory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Order extends AppCompatActivity{
    ListView lv;
    SearchView sv;
    ArrayAdapter<String> adapter;
    String[] data = {"Arjun","Ankit","Arvind","Dipesh","Dinesh","Deven"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        lv = (ListView) findViewById(R.id.idlistview);
        sv = (SearchView) findViewById(R.id.idsearch);
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
        //sv.setOnQueryTextListener(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.59.60.164:9002/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Inventory_list inventory_list = retrofit.create(Inventory_list.class);
        Call<List<Inventory>> call = inventory_list.getData();
        call.enqueue(new Callback<List<Inventory>>() {

            @Override

            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                Log.d("Order", "onResponse: Server Response: " + response.toString());
                Log.d("Order", "onResponse: received information: " + response.body().size());
                Log.d("Order", "came here");
                //String[] names = new String[5];
                int j = response.body().size();
                String[] datas = new String[response.body().size()];

                int i=0;
                for (Inventory feed : response.body()) {
                    Log.i("Order", feed.getName());
                    datas[i] = feed.getName();
                    i++;
                }
                List<Inventory> names = response.body();
                final ArrayAdapter adapter = new ArrayAdapter(Order.this, android.R.layout.simple_list_item_1, datas);
                lv.setAdapter(adapter);
                sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String arg0) {

                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        String text = newText;
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Log.e("Order", "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(Order.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
