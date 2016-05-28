package com.darien.openweathertest.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.darien.openweathertest.view.InfoFragment;
import com.darien.openweathertest.view.LocationFragment;

/**
 * Created by Darien
 * on 5/26/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.

        if (position == 0) {
            return LocationFragment.newInstance();
        } else {
            return InfoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Location";
            case 1:
                return "More Info";
        }
        return null;
    }
}
