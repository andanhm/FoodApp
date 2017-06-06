package app.andanhm.foodworld.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.andanhm.foodworld.R;
import app.andanhm.foodworld.model.FoodData;
import app.andanhm.foodworld.utils.Utility;
import app.andanhm.foodworld.view.fragment.FoodFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    android.support.v7.widget.Toolbar mToolbar;
    Activity mActivity;
    Utility mUtility;
    public static List<FoodData> mCartItemList;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_activity_main);
        mActivity = MainActivity.this;
        mUtility = new Utility(mActivity);
        mCartItemList = new ArrayList<>();
        //Setup the DrawerLayout and NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);

        // Lets inflate the very first fragment
        //Here , we are inflating the TabFragment as the first Fragment
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new FoodFragment()).commit();
        //Setup click events on the Navigation View Items.
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.menuMyHome:
                        Intent mIntent = new Intent(mActivity, MainActivity.class);
                        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mIntent);
                        break;
                    case R.id.menuMyOrder:
                        mUtility.toast(R.string.working_on_it);
                        break;
                    case R.id.menuLocation:
                        mUtility.toast(R.string.working_on_it);
                        break;
                    case R.id.menuSetting:
                        mUtility.toast(R.string.working_on_it);
                        break;
                    case R.id.menuAboutUs:
                        mUtility.toast(R.string.working_on_it);
                        break;
                }
                return false;
            }

        });

        //Setup Drawer Toggle of the Toolbar
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        assert mToolbar != null;
        mToolbar.setTitle(R.string.toolbar_title);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }
}
