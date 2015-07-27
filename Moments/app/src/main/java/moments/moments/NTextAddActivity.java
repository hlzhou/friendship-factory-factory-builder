package moments.moments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import fffb.moments.app.TextInputDialog;

public class NTextAddActivity extends MomentPromptWidgetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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