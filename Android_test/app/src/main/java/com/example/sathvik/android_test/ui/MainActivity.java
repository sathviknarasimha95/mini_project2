package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.models.ProdMenu;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    String FileName = "Login_fine";
    private SliderLayout Imgslider;
    private RecyclerView recyclerView;
    private ProdMenuAdapter adapter;
    private List<ProdMenu> menuList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*Button order = (Button) findViewById(R.id.order);

        order.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Intent gotoOrder = new Intent(getApplicationContext(),Order.class);
                startActivity(gotoOrder);
            }
        }

        );*/
        imgslider();
        create_recyclerview();
    }
    // image slider
    public void imgslider()
    {
        Imgslider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Img0",R.drawable.imgslider_4);
        file_maps.put("Img1",R.drawable.imgslider_1);
        file_maps.put("Img2",R.drawable.imgslider_2);
        file_maps.put("Img3",R.drawable.imgslider_3);
        file_maps.put("Img4", R.drawable.med1);

        for(String name : file_maps.keySet()){
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    //.description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra",name);

            Imgslider.addSlider(defaultSliderView);
        }
        Imgslider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        Imgslider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        Imgslider.setCustomAnimation(new DescriptionAnimation());
        Imgslider.setDuration(4000);
        Imgslider.addOnPageChangeListener(this);

    }
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        Imgslider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    // recycler view
    public void create_recyclerview()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        menuList = new ArrayList<>();
        adapter = new ProdMenuAdapter(this, menuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareMenu();

        try {
           // Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void prepareMenu() {
        int[] covers = new int[]{
                R.drawable.all,
                R.drawable.tab,
                R.drawable.syruo,
                R.drawable.inj,
                R.drawable.scope,
                };

        ProdMenu a = new ProdMenu("All",covers[0]);
        menuList.add(a);

        a = new ProdMenu("Tablets",covers[1]);
        menuList.add(a);

        a = new ProdMenu("Syrups", covers[2]);
        menuList.add(a);

        a = new ProdMenu("Injuction",covers[3]);
        menuList.add(a);

        a = new ProdMenu("Equipments",covers[4]);
        menuList.add(a);

        adapter.notifyDataSetChanged();
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    //toolbar menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            AlertDialog.Builder logoutalert = new AlertDialog.Builder(MainActivity.this);
            logoutalert.setTitle("Logout");
            logoutalert.setMessage("Are you Sure?");
            logoutalert.setCancelable(false);
            String YesButtonText = "Yes";
            String NoButtonText = "No";
            logoutalert.setPositiveButton(YesButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {



            SharedPreferences LoginSharedPref = getSharedPreferences(FileName, Context.MODE_PRIVATE);
            LoginSharedPref.edit().remove("Username").commit();
            LoginSharedPref.edit().remove("Token").commit();
            Intent gotoslogin = new Intent(getApplicationContext(),Login.class);
            startActivity(gotoslogin);
                }
            });
            logoutalert.setNegativeButton(NoButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            logoutalert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // back button handle
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Exit");
        alert.setMessage("Are You Sure to Exit?");
        alert.setCancelable(false);
        String yesButtonText = "Yes";
        String NoButtonText = "No";
        alert.setPositiveButton(yesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent exitapp = new Intent(Intent.ACTION_MAIN);
                exitapp.addCategory(Intent.CATEGORY_HOME);
                startActivity(exitapp);
            }
        });
        alert.setNegativeButton(NoButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }
}
