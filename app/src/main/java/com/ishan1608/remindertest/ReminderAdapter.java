package com.ishan1608.remindertest;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;


/**
 * Created by ishan on 5/6/15.
 */
public class ReminderAdapter extends FragmentPagerAdapter {
    public ReminderAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WaterReminderFragment();
            case 1:
                return new ActivityReminderFragment();
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 2;
    }
}
