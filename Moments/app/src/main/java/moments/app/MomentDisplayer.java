package moments.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import moments.app.database.Data;

/**
 * Handles the displaying of moments retrieved from the jar
 */
public class MomentDisplayer {

	public static boolean showMoment(Activity activity, Data data) {
		return showMoment(activity, data, null);
	}

	public static boolean showMoment(Activity activity, Data data, JarDismissListener jarDismissListener) {
		switch(data.getType()) {
		case TEXT:
			return showTextMoment(activity, data.getText(), jarDismissListener);
		case IMAGE:
			return showImageMoment(activity, data.getText(), jarDismissListener);
		case LINK:
			return showLinkMoment(activity, data.getText(), jarDismissListener);
		}
		return false;
	}
	
	private static boolean showTextMoment(Activity activity, String text, final JarDismissListener jarDismissListener){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(text);
    	AlertDialog dialog = builder.create();
		dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (jarDismissListener != null) {
                    jarDismissListener.onJarOpenDismiss();
                }
            }
        });

		dialog.show();

        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        Typeface ralewayFont = Typeface.createFromAsset(activity.getApplicationContext().getAssets(), "fonts/raleway_italics.ttf");
        textView.setTypeface(ralewayFont);

        return true;
	}

	private static boolean showLinkMoment(Activity activity, String text, JarDismissListener jarDismissListener) {
		if (!text.startsWith("https://") && !text.startsWith("http://")){
		    text = "http://" + text;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(text));
		activity.startActivity(i);
		if (jarDismissListener != null) {
			jarDismissListener.onJarOpenDismiss();
		}
		return true;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private static boolean showImageMoment(Activity activity, String img, final JarDismissListener jarDismissListener) {
	    Uri imageUri = Uri.parse(img);
	    
	 	Display display = activity.getWindowManager().getDefaultDisplay();
	 	Point size;
		size = new Point();
		display.getSize(size);
	 	int screenWidth = size.x;
	 	int screenHeight = size.y;

	 	Bitmap bitmap;
		try {
			bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), imageUri);
		} catch (IOException e) {
			return false;
		}
	 	int bitmapHeight = bitmap.getHeight();
	 	int bitmapWidth = bitmap.getWidth();
	 	
	 	// Scale the image down
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

	    dialog.getWindow().setBackgroundDrawable(null);

		dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialogInterface) {
				if(jarDismissListener != null) {
					jarDismissListener.onJarOpenDismiss();
				}
			}
		});

	    dialog.show();
	    return true;
	}
}
