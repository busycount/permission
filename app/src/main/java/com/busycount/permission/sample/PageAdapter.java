package com.busycount.permission.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * PageAdapter
 * <p>
 * 2018/12/10 | Count.C | Created
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return BlankFragment.newInstance("a" + i, "b" + i);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
