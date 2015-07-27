package moments.moments;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hzhou1235 on 7/26/15.
 */
public class RalewayTextView extends TextView {

    public RalewayTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                "fonts/raleway_italics.ttf"));
    }
}