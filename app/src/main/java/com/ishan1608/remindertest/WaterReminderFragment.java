package com.ishan1608.remindertest;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ishan1608.remindertest.service.WaterReminderAlarmReceiver;
import com.ishan1608.remindertest.service.WaterReminderService;

public class WaterReminderFragment extends Fragment {
    private static final String TAG = "WaterReminderFragment";

    private Button repeatedReminderButton;
    private Intent waterReminderIntent;
    private Intent waterRepeatedReminderIntent;
    private Button repeatedReminderCancelButton;
    private Intent waterRepeatedReminderCancelIntent;

    private AlarmManager waterReminderAlarmManager;
    private Button reminderButton;
    private Button startAlarmButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.water_reminder_fragment, null);
        // Getting handle
        reminderButton = (Button) rootView.findViewById(R.id.reminder_button);
        repeatedReminderButton = (Button) rootView.findViewById(R.id.repeated_reminder_button);
        repeatedReminderCancelButton = (Button) rootView.findViewById(R.id.repeated_reminder_cancel_button);
        startAlarmButton = (Button) rootView.findViewById(R.id.start_alarm_button);

        // Click handling for the buttons

        repeatedReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "repeatedReminderButton clicked");
                // Enabling cancel button
                repeatedReminderCancelButton.setEnabled(true);
                // Disabling repeated reminder button
                repeatedReminderButton.setEnabled(false);
                waterRepeatedReminderIntent = new Intent(getActivity(), WaterReminderService.class);
                waterRepeatedReminderIntent.setAction(WaterReminderService.WATER_REMINDER_TASK_REPEATED);
                getActivity().startService(waterRepeatedReminderIntent);
            }
        });

        repeatedReminderCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "repeatedReminderCancelButton clicked");
                // Enabling repeated reminder button
                repeatedReminderButton.setEnabled(true);
                // Disabling cancel button
                repeatedReminderCancelButton.setEnabled(false);
                waterRepeatedReminderCancelIntent = new Intent(getActivity(), WaterReminderService.class);
                waterRepeatedReminderCancelIntent.setAction(WaterReminderService.WATER_REMINDER_TASK_REPEATED_CANCEL);
                getActivity().startService(waterRepeatedReminderCancelIntent);
            }
        });
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "reminderButton clicked");
                waterReminderIntent = new Intent(getActivity(), WaterReminderService.class);
                waterReminderIntent.setAction(WaterReminderService.WATER_REMINDER_TASK);
                getActivity().startService(waterReminderIntent);
            }
        });

        startAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "startAlarmButton clicked");
                waterReminderAlarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent waterReminderAlarmIntent = new Intent(getActivity(), WaterReminderAlarmReceiver.class);
                PendingIntent waterAlarmPendingIntent = PendingIntent.getBroadcast(getActivity(), 0, waterReminderAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                // Once alarm setup
//                waterReminderAlarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 5 * 1000, waterAlarmPendingIntent);

                // Every minute alarm case
                waterReminderAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 60 * 1000, waterAlarmPendingIntent);
            }
        });
        return rootView;
    }
}
