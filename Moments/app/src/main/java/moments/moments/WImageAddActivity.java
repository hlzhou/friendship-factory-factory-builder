package moments.moments;

/**
 * Created by hzhou1235 on 6/27/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by hzhou1235 on 6/27/15.
 */
public class WImageAddActivity extends MomentPromptActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HELEN", "W_IMAGE_ADD");
        super.showPrompt(Type.IMAGE, true);
    }
}
