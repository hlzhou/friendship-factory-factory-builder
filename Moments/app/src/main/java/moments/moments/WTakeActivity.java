package moments.moments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WTakeActivity extends MomentPromptActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HELEN", "YAYY IT GOT TO WIDGETACTIVITY ONCREATE");
        //setContentView(R.layout.appwidget_jar);
        MomentDisplayer.showMoment(this, getNote());
        finish();

    }
}
