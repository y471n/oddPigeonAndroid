package com.example.mac.oddpigeon;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class MainActivityCollectionPagerAdapter extends FragmentPagerAdapter
{
    private static Integer numberOfTabs = 4;

    public MainActivityCollectionPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch (i){
            case 0:
                fragment = new OpenCirclesListFragment();
                break;
            case 1:
                fragment = new ClosedCirclesListFragment();
                break;
            case 2:
                fragment = new CreateCircleFragment();
                break;
            case 3:
                fragment = new FriendListFragment();
                break;
            default:
                fragment = new OpenCirclesListFragment();
                break;
        }
//        Bundle args = new Bundle();
//        Fragment fragment = new DemoObjectFragment();
//        Bundle args = new Bundle();
//        // Our object is just an integer :-P
//        args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }

}
