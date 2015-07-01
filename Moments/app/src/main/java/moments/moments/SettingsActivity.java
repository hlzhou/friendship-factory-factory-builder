package moments.moments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Set;

public class SettingsActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener {

    //if id, then notification_switch
    public static final String NOTIFICATION_STATE = "notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        if (key.equals(NOTIFICATION_STATE)) {
            if (sharedPreferences.getBoolean(key, true)) {
                HappyNotificationManager.startDailyNotification(getApplicationContext());
            }
            else {
                HappyNotificationManager.stopDailyNotification(getApplicationContext());
            }
        }
    }
}
