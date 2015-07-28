package moments.app.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import moments.app.R;
import moments.app.widget.WImageAddActivity;
import moments.app.widget.WLinkAddActivity;
import moments.app.widget.WTextAddActivity;

public class MomentsAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent textIntent = new Intent(context, WTextAddActivity.class);
            textIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingTextIntent = PendingIntent.getActivity(context, 0, textIntent, 0);

            Intent imageIntent = new Intent(context, WImageAddActivity.class);
            imageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingImageIntent = PendingIntent.getActivity(context, 0, imageIntent, 0);

            Intent linkIntent = new Intent(context, WLinkAddActivity.class);
            linkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingLinkIntent = PendingIntent.getActivity(context, 0, linkIntent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
            views.setOnClickPendingIntent(R.id.add_text, pendingTextIntent);
            views.setOnClickPendingIntent(R.id.add_image, pendingImageIntent);
            views.setOnClickPendingIntent(R.id.add_link, pendingLinkIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

    @Override
    public void onReceive(Context context, Intent intent){
        // Chain up to the super class so the onEnabled, etc callbacks get dispatched
        super.onReceive(context, intent);
    }

}
