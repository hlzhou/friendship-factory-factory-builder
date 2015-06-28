package moments.moments;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class MomentsAppWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch WidgetActivity
            Intent textIntent = new Intent(context, WTextAddActivity.class);
            textIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingTextIntent = PendingIntent.getActivity(context, 0, textIntent, 0);

            Intent imageIntent = new Intent(context, WImageAddActivity.class);
            imageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingImageIntent = PendingIntent.getActivity(context, 0, imageIntent, 0);

            Intent linkIntent = new Intent(context, WLinkAddActivity.class);
            linkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingLinkIntent = PendingIntent.getActivity(context, 0, linkIntent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget); //TODO: the layout might be something else
            views.setOnClickPendingIntent(R.id.add_text, pendingTextIntent);
            views.setOnClickPendingIntent(R.id.add_image, pendingImageIntent);
            views.setOnClickPendingIntent(R.id.add_link, pendingLinkIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

    public void onReceive(){

    }

}
