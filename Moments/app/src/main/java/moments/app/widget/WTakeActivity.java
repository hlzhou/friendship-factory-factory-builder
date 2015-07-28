package moments.app.widget;

import android.os.Bundle;

import moments.app.JarDismissListener;
import moments.app.MomentDisplayer;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WTakeActivity extends MomentPromptWidgetActivity implements JarDismissListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MomentDisplayer.showMoment(this, getNote(), this);
    }

    @Override
    public void onJarOpenDismiss() {
        finish();
        System.exit(0);
    }
}
