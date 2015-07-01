package moments.moments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 *
 */
public class HappyNotificationManager extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            startDailyNotification(context);
        }
    }

    public static void startDailyNotification(Context context) {
        if(isDailyNotificationStarted(context)) {
            return;
        }
        PendingIntent pendingIntent = getNotifiationPendingIntent(context);
        AlarmManager alarmMgr = getAlarmManager(context);
       /*alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                AlarmManager.INTERVAL_HALF_DAY,
                AlarmManager.INTERVAL_DAY, pendingIntent);*/
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                2000,
                2000, pendingIntent);
        ComponentName receiver = new ComponentName(context, HappyNotificationManager.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public static void stopDailyNotification(Context context) {
        if (isDailyNotificationStarted(context)) {
            PendingIntent pendingIntent = getNotifiationPendingIntent(context);
            AlarmManager alarmMgr = getAlarmManager(context);
            alarmMgr.cancel(pendingIntent);
            pendingIntent.cancel();
        }
        ComponentName receiver = new ComponentName(context, HappyNotificationManager.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }


    public static boolean isDailyNotificationStarted(Context context) {
        return PendingIntent.getService(context, 0,
                getNotificationIntent(context), PendingIntent.FLAG_NO_CREATE) != null;
    }

    private static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
    }

    private static PendingIntent getNotifiationPendingIntent(Context context) {
        Intent intent = getNotificationIntent(context);
        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Intent getNotificationIntent(Context context) {
        return new Intent(context, NotificationService.class);
    }
}