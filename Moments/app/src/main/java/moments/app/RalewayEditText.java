package moments.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Convenience class for EditTexts with font already set
 */
public class RalewayEditText extends EditText{
    public RalewayEditText(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                "fonts/raleway.ttf"));
    }
}
