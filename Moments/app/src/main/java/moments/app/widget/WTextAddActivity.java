package moments.app.widget;

import android.content.Intent;
import android.os.Bundle;

import moments.app.TextInputDialog;
import moments.app.Type;

public class WTextAddActivity extends MomentPromptWidgetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.showPrompt(Type.TEXT);
    }
}
