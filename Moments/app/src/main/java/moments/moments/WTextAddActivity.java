package moments.moments;

import android.content.Intent;
import android.os.Bundle;

import fffb.moments.app.TextInputDialog;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WTextAddActivity extends MomentPromptWidgetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //TODO: move somewhere else? seems to only work after restarting launcher...
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent.hasExtra(TextInputDialog.PROMPT_KEY)) {
            String prompt = intent.getExtras().getString(TextInputDialog.PROMPT_KEY);
            intent.removeExtra(TextInputDialog.PROMPT_KEY);
            super.showTextWithPrompt(prompt);
        } else {
            super.showPrompt(Type.TEXT);
        }
    }

}
