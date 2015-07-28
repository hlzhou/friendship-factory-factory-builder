package moments.app.widget;

/**
 * Created by hzhou1235 on 6/27/15.
 */

import android.os.Bundle;

import moments.app.Type;


/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WImageAddActivity extends MomentPromptWidgetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.showPrompt(Type.IMAGE);
    }
}
