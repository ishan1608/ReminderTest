package com.ishan1608.remindertest.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class AutoStart extends BroadcastReceiver {
    private static final String TAG = AutoStart.class.getSimpleName();

    public AutoStart() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Starting water reminder alarm
        Log.i(TAG, "AutoStart -> onReceive clicked");
        AlarmManager waterReminderAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent waterReminderAlarmIntent = new Intent(context, WaterReminderAlarmReceiver.class);
        PendingIntent waterAlarmPendingIntent = PendingIntent.getBroadcast(context, 0, waterReminderAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Every minute alarm
        waterReminderAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 60 * 1000, waterAlarmPendingIntent);
    }
}
