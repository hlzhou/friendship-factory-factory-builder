package moments.moments;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

import fffb.moments.app.TextInputDialog;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WTextAddActivity extends MomentPromptWidgetActivity {
    public static final String TAG = "WTextAddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) { //TODO: move somewhere else? seems to only work after restarting launcher...
        super.onCreate(savedInstanceState);
        Log.d("HELEN", "W_TEXT_ADD");

        Intent intent = getIntent();
        if (intent.hasExtra(TextInputDialog.PROMPT_KEY)) {
            String prompt = intent.getExtras().getString(TextInputDialog.PROMPT_KEY);
            Log.d(TAG, "Has extra - putting in " + prompt);
            super.showTextWithPrompt(prompt);
        } else {
            Log.d(TAG, "No prompt, showing default.");
            super.showPrompt(Type.TEXT);
        }
    }

}
