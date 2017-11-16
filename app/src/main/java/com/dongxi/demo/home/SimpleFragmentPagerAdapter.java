package com.dongxi.demo.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by macmini002 on 17/9/16.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGGE_COUNT = 3 ;
    private String tabTitles[] = new String[]{"Tab1","Tab2","Tab3"};
    private Context content ;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context content) {
        super(fm);
        this.content = content ;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return PAGGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
