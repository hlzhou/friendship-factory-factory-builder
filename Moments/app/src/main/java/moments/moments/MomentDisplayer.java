package moments.moments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.ImageView;

import java.io.IOException;


public class MomentDisplayer {
	public static boolean showMoment(Activity activity, Data data) {
		switch(data.getType()) {
		case TEXT:
			return showTextMoment(activity, data.getText());
		case IMAGE:
			return showImageMoment(activity, data.getText());
		case LINK:
			return showLinkMoment(activity, data.getText());
		}
		return false;
	}
	
	private static boolean showTextMoment(Activity activity, String text){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(text);
    	builder.show();
		return true;
	}

	private static boolean showLinkMoment(Activity activity, String text) {
		if (!text.startsWith("https://") && !text.startsWith("http://")){
		    text = "http://" + text;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(text));
		activity.startActivity(i);
		return true;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private static boolean showImageMoment(Activity activity, String img) {
	    // Get the layout inflater
	    LayoutInflater inflater = activity.getLayoutInflater();
	    
	    Uri imageUri = Uri.parse(img);
	    
	    // Get screen size
	 	Display display = activity.getWindowManager().getDefaultDisplay();
	 	Point size;
		size = new Point();
		display.getSize(size);
	 	int screenWidth = size.x;
	 	int screenHeight = size.y;
	 	
	 	// Get target image size
	 	Bitmap bitmap;
		try {
			bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), imageUri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	 	int bitmapHeight = bitmap.getHeight();
	 	int bitmapWidth = bitmap.getWidth();
	 	
	 	// Scale the image down to fit perfectly into the screen
	 	// The value (250 in this case) must be adjusted for phone/tables displays
	 	while(bitmapHeight > (screenHeight - 10) || bitmapWidth > (screenWidth - 10)) {
	 		bitmapHeight = bitmapHeight / 2;
	 		bitmapWidth = bitmapWidth / 2;
	 	}

	 	// Create resized bitmap image
	 	BitmapDrawable resizedBitmap = new BitmapDrawable(activity.getResources(), Bitmap.createScaledBitmap(bitmap, bitmapWidth, bitmapHeight, false));

	 	// Create dialog
	    Dialog dialog = new Dialog(activity);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    dialog.setContentView(R.layout.image_moment_dialog);
	    ImageView image = (ImageView) dialog.findViewById(R.id.image);
	    image.setBackground(resizedBitmap);
	    
	    // Without this line there is a very small border around the image (1px)
	    // In my opinion it looks much better without it, so the choice is up to you.
	    dialog.getWindow().setBackgroundDrawable(null);

	    // Show the dialog
	    dialog.show();
	    return true;
	}
}
