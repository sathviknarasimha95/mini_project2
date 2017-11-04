package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.Orderadapter;
import com.example.sathvik.android_test.api.Inventory_list;
import com.example.sathvik.android_test.models.Inventory;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.github.rubensousa.raiflatbutton.R.styleable.View;


public class Order extends BaseActivity{
    ListView lv;
    SearchView sv;
    ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        lv = (ListView) findViewById(R.id.idlistview);
        sv = (SearchView) findViewById(R.id.idsearch);
        Context context;
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
        //sv.setOnQueryTextListener(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Inventory_list inventory_list = retrofit.create(Inventory_list.class);
        Call<List<Inventory>> call = inventory_list.getData();
        progressbar();

        call.enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                Log.d("Order", "onResponse: Server Response: " + response.toString());
                Log.d("Order", "onResponse: received information: " + response.body().size());
                Log.d("Order", "came here");
                //String[] names = new String[5];
                int j = response.body().size();
                String[] prod_name = new String[response.body().size()];
                String[] prod_id = new String[response.body().size()];
                String[] prod_type = new String[response.body().size()];
                String[] prod_company = new String[response.body().size()];
                String[] prod_price = new String[response.body().size()];


                int i=0;
                for (Inventory feed : response.body()) {
                    Log.i("Order", feed.getProductName());
                    prod_id[i] = feed.getProductId();
                    prod_name[i] = feed.getProductName();
                    prod_type[i] = feed.getProductType();
                    prod_company[i] = feed.getProductCompany();
                    prod_price[i] = feed.getProductPrice();
                    i++;
                }

                List<Inventory> names = response.body();
                //final ArrayAdapter adapter = new ArrayAdapter(Order.this,R.layout.listorder,R.id.list_order,datas);

                final Orderadapter adapter = new Orderadapter(getApplicationContext(),prod_name,prod_type,prod_company,prod_price,prod_id);
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
                dismissprogressbar();
                Log.e("Order", "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(Order.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        dismissprogressbar();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    public void progressbar()
    {
        progress = new MaterialDialog.Builder(this)
                .title("Order Items")
                .content("Please Wait")
                .progress(true, 0)
                .show();
    }

    public void dismissprogressbar()
    {
        //progress.dismiss();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progress.dismiss();
            }
        }, 1);
    }


}
