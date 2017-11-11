package com.example.sathvik.android_test.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sathvik.android_test.R;
import com.example.sathvik.android_test.adapters.AdminMenuAdapter;
import com.example.sathvik.android_test.adapters.Admin_Adapter;
import com.example.sathvik.android_test.adapters.ProdMenuAdapter;
import com.example.sathvik.android_test.models.AdminMenu;
import com.example.sathvik.android_test.models.ProdMenu;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends BaseActivity {
    String[] titles = {"Ongoing Orders","Completed Orders","Pending Order","Payments","Order History"};
    ListView Admin_list;
    private RecyclerView recyclerView;
    private List<AdminMenu> menuList;
    private AdminMenuAdapter adapter;
    private Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        /*Admin_list = (ListView) findViewById(R.id.Admin_main);
        Admin_Adapter adapter = new Admin_Adapter(getApplicationContext(),titles);
        Admin_list.setAdapter(adapter);*/

        create_recyclerview();

    }

    public void create_recyclerview()
    {
        recyclerView = (RecyclerView) findViewById(R.id.admin_recycler_view);

        menuList = new ArrayList<>();
        adapter = new AdminMenuAdapter(this, menuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new AdminActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
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
                R.raw.ongoing1,
                R.raw.completed,
                R.raw.pending,
                R.raw.payments,
                R.raw.history,
                R.raw.cancled,
        };

        AdminMenu a = new AdminMenu("Pending Orders",covers[2]);
        menuList.add(a);

        a = new AdminMenu("Ongoing Orders",covers[0]);
        menuList.add(a);

        a = new AdminMenu("Completed Orders", covers[1]);
        menuList.add(a);

        a = new AdminMenu("Payments",covers[3]);
        menuList.add(a);

        a = new AdminMenu("Order History",covers[4]);
        menuList.add(a);

        a = new AdminMenu("Cancelled Orders",covers[5]);
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
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(AdminActivity.this);
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
