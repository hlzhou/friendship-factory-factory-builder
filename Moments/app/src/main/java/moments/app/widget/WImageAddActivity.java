package moments.app.widget;

import android.os.Bundle;

import moments.app.Type;

public class WImageAddActivity extends MomentPromptWidgetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.showPrompt(Type.IMAGE);
    }
}
