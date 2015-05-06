package com.ishan1608.remindertest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ishan1608.remindertest.service.WaterReminderService;

public class WaterReminderFragment extends Fragment {
    private static final String TAG = "WaterReminderFragment";
    private View rootView;
    private Button reminderButton;
    private Button repeatedReminderButton;
    private Intent waterReminderIntent;
    private Intent waterRepeatedReminderIntent;
    private Button repeatedReminderCancelButton;
    private Intent waterRepeatedReminderCancelIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.water_reminder_fragment, null);
        // Getting handle
        reminderButton = (Button) rootView.findViewById(R.id.reminder_button);
        repeatedReminderButton = (Button) rootView.findViewById(R.id.repeated_reminder_button);
        repeatedReminderCancelButton = (Button) rootView.findViewById(R.id.repeated_reminder_cancel_button);

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
        return rootView;
    }
}
