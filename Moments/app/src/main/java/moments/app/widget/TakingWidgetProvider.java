package moments.app.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import moments.app.R;

public class TakingWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent takeIntent = new Intent(context, WTakeActivity.class);
            takeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingTakeIntent = PendingIntent.getActivity(context, 0, takeIntent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_jar); //TODO: the layout might be something else
            views.setOnClickPendingIntent(R.id.take_jar, pendingTakeIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
