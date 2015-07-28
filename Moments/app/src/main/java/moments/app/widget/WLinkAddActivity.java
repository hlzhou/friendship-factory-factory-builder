package moments.app.widget;

/**
 * Created by hzhou1235 on 6/27/15.
 */

import android.os.Bundle;
import android.util.Log;

import moments.app.Type;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WLinkAddActivity extends MomentPromptWidgetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HELEN", "W_LINK_ADD");
        super.showPrompt(Type.LINK);
    }
}
