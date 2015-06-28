package com.fffb.moment;

import com.moments.moments.DataDbAdapter;
import com.moments.moments.Type;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

public class MomentPromptActivity extends Activity implements TextInputDialog.TextSubmitter, LinkInputDialog.LinkSubmitter{
	private int mDataNumber = 1;
    private DataDbAdapter mDbHelper;
    
    public static final int RESULT_LOAD_IMAGE = 1;
    public static final int RESULT_TAKE_IMAGE = 2;
    
	public static void showPrompt(final MomentPromptActivity activity, Type type) {
		switch(type){
		case TEXT:
			TextInputDialog tDialog = new TextInputDialog();
	    	tDialog.show(activity.getFragmentManager(), "textInput");
	    	break;
		case IMAGE:
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
	    	builder.setTitle(R.string.image_source_prompt)
	    	.setItems(R.array.image_sources, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            // The 'which' argument contains the index position
	            // of the selected item
	            switch(which){
	            case 0:
	            	Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	            	activity.startActivityForResult(takePicture, RESULT_TAKE_IMAGE);
	            	break;
	            case 1:
	            	Intent getImageFromGallery = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                activity.startActivityForResult(getImageFromGallery, RESULT_LOAD_IMAGE);
	            	break;
	            }
	        }
	    	});
	    	builder.show();
	    	break;
		case LINK:
			LinkInputDialog lDialog = new LinkInputDialog();
	    	lDialog.show(activity.getFragmentManager(), "linkInput");
	    	break;
		}
	}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbHelper = new DataDbAdapter(this);
        mDbHelper.open();
    }
    
    public void submitText(String text) {
    	Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    	createNote(Type.TEXT, text);
    }
    
    @Override
	public void submitLink(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    	createNote(Type.LINK, text);
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
        case RESULT_LOAD_IMAGE:
        	if(resultCode == RESULT_OK && data != null) {
        		Uri selectedImage = data.getData();
        		Toast.makeText(this, "Got an image from gallery: " + selectedImage.toString(), Toast.LENGTH_SHORT).show();
                createNote(Type.IMAGE, selectedImage.toString());
        	}
        	break;
        case RESULT_TAKE_IMAGE:
        	if(resultCode == RESULT_OK && data != null) {
        		Uri selectedImage = data.getData();
        		Toast.makeText(this, "Took an image from the camera: " + selectedImage.toString(), Toast.LENGTH_SHORT).show();
                createNote(Type.IMAGE, selectedImage.toString());
        	}
        	break;
        }
    }
    
    private void createNote(Type type, String text) {
        String dataName = "Note " + mDataNumber++;
        mDbHelper.createNote(Type.TEXT, "test");
    }
	
}
