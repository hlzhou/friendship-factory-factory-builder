package moments.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Opened when a user decides to share something to Moments
 */
public class LightInputActivity extends MomentPromptActivity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getType();
        Type mType;
        if(type.equals("text/plain")){
        	if (!type.startsWith("https://") && !type.startsWith("http://")){
    		    mType = Type.TEXT;
    		}else {
    			mType = Type.LINK;
    		}
        	String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        	createNote(mType, text);
        }else {
        	mType = Type.IMAGE;
        	Uri imageUri = (Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM);
        	createNote(mType, imageUri.toString());
        }
        Toast.makeText(this, "Saving happy moment!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
