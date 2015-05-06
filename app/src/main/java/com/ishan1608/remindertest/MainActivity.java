package com.ishan1608.remindertest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;


public class MainActivity extends Activity {

    private ViewPager reminderViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Getting handles for views
        reminderViewPager = (ViewPager) findViewById(R.id.reminder_container_view_pager);
        // Setting adapter for view pager
        reminderViewPager.setAdapter(new ReminderAdapter(getFragmentManager()));
    }

}
