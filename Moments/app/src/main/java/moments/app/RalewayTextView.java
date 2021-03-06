package moments.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Convenience class for TextView with font already set
 */
public class RalewayTextView extends TextView {

    public RalewayTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                "fonts/raleway_italics.ttf"));
    }
}