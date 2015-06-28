package moments.moments;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WTextAddActivity extends MomentPromptActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HELEN", "W_TEXT_ADD");
        super.showPrompt(Type.TEXT);
    }
}
