package com.example.mac.oddpigeon;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;


public class MainActivity extends ActionBarActivity implements OpenCirclesListFragment.OnFragmentInteractionListener, CreateCircleFragment.OnFragmentInteractionListener, ClosedCirclesListFragment.OnFragmentInteractionListener, FriendListFragment.OnFragmentInteractionListener {


//    final actionBar =
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    MainActivityCollectionPagerAdapter mMainActivityCollectionPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mMainActivityCollectionPagerAdapter = new MainActivityCollectionPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.main_pager);
        mViewPager.setAdapter(mMainActivityCollectionPagerAdapter);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between pages, select the
                // corresponding tab.
                getActionBar().setSelectedNavigationItem(position);
            }
        });


        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // show the given tab
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        // Add 4 tabs, specifying the tab's text and TabListener
        actionBar.addTab(actionBar.newTab().setText("Open Circles").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        }));
        actionBar.addTab(actionBar.newTab().setText("Closed Circles").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        }));
        actionBar.addTab(actionBar.newTab().setText(" + ").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        }));
        actionBar.addTab(actionBar.newTab().setText("Introduced List").setTabListener(new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        }));
//            actionBar.addTab(actionBar.newTab().setText("Open Circles").setTabListener((android.support.v7.app.ActionBar.TabListener) tabListener));
//            actionBar.addTab(actionBar.newTab().setText("Closed Circles").setTabListener((android.support.v7.app.ActionBar.TabListener) tabListener));
//            actionBar.addTab(actionBar.newTab().setText("Create Circles").setTabListener((android.support.v7.app.ActionBar.TabListener) tabListener));
//            actionBar.addTab(actionBar.newTab().setText("Friend List").setTabListener((android.support.v7.app.ActionBar.TabListener) tabListener));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }
}


