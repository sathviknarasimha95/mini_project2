package com.example.sathvik.android_test.ui;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.ProdMenuAdapter;
import com.example.sathvik.android_test.models.ProdMenu;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    String FileName = "Login_fine";
    private SliderLayout Imgslider;
    private RecyclerView recyclerView;
    private ProdMenuAdapter adapter;
    private List<ProdMenu> menuList;
    private Drawer result;
    private CoordinatorLayout coordinatorLayout;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        setSupportActionBar(toolbar);
        navbar(toolbar,savedInstanceState);
        imgslider();
        //check_network();
        create_recyclerview();
    }
    // navigation Drawer
    public void navbar(Toolbar toolbar,Bundle savedInstanceState)
    {
        result = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.header)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(new PrimaryDrawerItem().withName("Profile"),new PrimaryDrawerItem().withName("Orders History"),new PrimaryDrawerItem().withName("Logout"))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override

                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                                long id = drawerItem.getIdentifier();
                                int ch = (int)id%10;
                                //Toast.makeText(getApplicationContext(),ch+"",Toast.LENGTH_SHORT).show();
                                if(ch==-5 || ch==-1) {
                                    logout();
                                }
                        return false;
                    }
                }).build();
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
    }
    // image slider
    public void imgslider()
    {
        Imgslider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Img0",R.raw.imgslider_4);
        file_maps.put("Img1",R.raw.imgslider_1);
        file_maps.put("Img2",R.raw.imgslider_2);
        file_maps.put("Img3",R.raw.imgslider_3);
        file_maps.put("Img4", R.raw.med1);

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
                R.raw.all,
                R.raw.tab,
                R.raw.syruo,
                R.raw.inj,
                R.raw.scope,
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
        super.onCreateOptionsMenu(menu);
        /*getMenuInflater().inflate(R.menu.menu_main, menu);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

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
    public void check_network()
    {
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Internet is Connected", Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        Imgslider.destroyDrawingCache();
        super.onDestroy();
    }
}
