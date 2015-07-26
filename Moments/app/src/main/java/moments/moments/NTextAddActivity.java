package moments.moments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import fffb.moments.app.TextInputDialog;

public class NTextAddActivity extends MomentPromptWidgetActivity {
    public static final String TAG = "NTextAddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent.hasExtra(TextInputDialog.PROMPT_KEY)) {
            String prompt = intent.getExtras().getString(TextInputDialog.PROMPT_KEY);
            Log.d(TAG, "Has extra - putting in " + prompt);
            intent.removeExtra(TextInputDialog.PROMPT_KEY);
            super.showTextWithPrompt(prompt);
        } else {
            Log.d(TAG, "No prompt, showing default.");
            super.showPrompt(Type.TEXT);
        }
    }

}