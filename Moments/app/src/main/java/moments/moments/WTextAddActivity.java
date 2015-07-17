package moments.moments;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WTextAddActivity extends MomentPromptWidgetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) { //TODO: move somewhere else? seems to only work after restarting launcher...
        super.onCreate(savedInstanceState);
        Log.d("HELEN", "W_TEXT_ADD");
        super.showPrompt(Type.TEXT);
    }

}
