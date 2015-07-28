package moments.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by hzhou1235 on 7/26/15.
 */
public class RalewayEditText extends EditText{
    public RalewayEditText(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                "fonts/raleway.ttf"));
    }
}
