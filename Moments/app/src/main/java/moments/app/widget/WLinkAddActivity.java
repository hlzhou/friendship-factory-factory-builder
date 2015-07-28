package moments.app.widget;

import android.os.Bundle;
import android.util.Log;

import moments.app.Type;

public class WLinkAddActivity extends MomentPromptWidgetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.showPrompt(Type.LINK);
    }
}
