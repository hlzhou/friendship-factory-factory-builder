package moments.app.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import moments.app.QuestionManager;
import moments.app.QuestionManager.MomentQuestion;
import moments.app.R;
import moments.app.TextInputDialog;

/**
 * Launches notifications prompting users to add text to the jar.
 */
public class NotificationService extends Service {

    public class ServiceBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // The system notification manager
    private NotificationManager mNM;

    @Override
    public void onCreate() {
        Log.d("NotifyService", "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LocalService", "Received start id " + startId + ": " + intent);
        showNotification();
        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new ServiceBinder();

    private void showNotification() {
        CharSequence title = "Moments";
        int icon = R.drawable.jar;
        MomentQuestion question = QuestionManager.getRandomQuestion(getApplicationContext());
        CharSequence text = question.getQuestion();
        long time = System.currentTimeMillis();

        Notification notification = new Notification(icon, text, time);
        Intent addTextActivityIntent =  new Intent(this, NTextAddActivity.class);
        addTextActivityIntent.putExtra(TextInputDialog.PROMPT_KEY, question.getPrompt());

        // The PendingIntent to launch if the user selects the notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, addTextActivityIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        notification.setLatestEventInfo(this, title, text, contentIntent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNM.notify(NOTIFICATION, notification);

        stopSelf();
    }
}
