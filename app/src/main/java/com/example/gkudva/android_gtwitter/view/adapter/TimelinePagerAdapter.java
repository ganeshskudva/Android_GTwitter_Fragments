package com.example.gkudva.android_gtwitter.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gkudva.android_gtwitter.view.fragment.HomeTimelineListFragment;
import com.example.gkudva.android_gtwitter.view.fragment.MentionsTimelineListFragment;

/**
 * Created by gkudva on 06/10/17.
 */

public class TimelinePagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "HOME", "MENTION"};

    private Context mContext;
    private FragmentManager mFragmentManager;

    public TimelinePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mFragmentManager = fm;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return HomeTimelineListFragment.newInstance(mFragmentManager);
        } else if (position == 1){
            return MentionsTimelineListFragment.newInstance(mFragmentManager);
        } else {
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return "";
    }
}
