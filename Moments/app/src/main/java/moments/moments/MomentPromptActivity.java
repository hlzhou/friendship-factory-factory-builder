package moments.moments;

import moments.moments.DataDbAdapter;
import moments.moments.Type;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import moments.moments.MomentDisplayer;
import fffb.moments.app.LinkInputDialog;
import fffb.moments.app.TextInputDialog;

public class MomentPromptActivity extends Activity implements TextInputDialog.TextSubmitter, LinkInputDialog.LinkSubmitter{
	public int mDataNumber = 1;
    public DataDbAdapter mDbHelper;

    public static final int RESULT_LOAD_IMAGE = 1;
    public static final int RESULT_TAKE_IMAGE = 2;
    private boolean is_widget = false;
    
	public void showPrompt(Type type, boolean widget) {
		is_widget = widget;
        switch(type){
		case TEXT:
			TextInputDialog tDialog = new TextInputDialog();
	    	tDialog.show(getFragmentManager(), "textInput");
	    	break;
		case IMAGE:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setTitle(R.string.image_source_prompt)
	    	.setItems(R.array.image_sources, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            // The 'which' argument contains the index position
	            // of the selected item
	            switch(which){
	            case 0:
	            	Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	            	startActivityForResult(takePicture, RESULT_TAKE_IMAGE);
	            	break;
	            case 1:
	            	Intent getImageFromGallery = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                startActivityForResult(getImageFromGallery, RESULT_LOAD_IMAGE);
	            	break;
	            }
	        }
	    	});
	    	builder.show();
	    	break;
		case LINK:
			LinkInputDialog lDialog = new LinkInputDialog();
	    	lDialog.show(getFragmentManager(), "linkInput");
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
        showMomentCapturedToast();
    	createNote(Type.TEXT, text);
        if (is_widget){
            finish();
            System.exit(0);
        }
    }
    
    @Override
	public void submitLink(String text) {
        showMomentCapturedToast();
    	createNote(Type.LINK, text);
        if (is_widget){
            finish();
            System.exit(0);
        }
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
        case RESULT_LOAD_IMAGE:
        	if(resultCode == RESULT_OK && data != null) {
        		Uri selectedImage = data.getData();
                showMomentCapturedToast();
                onImageSubmit();
                createNote(Type.IMAGE, selectedImage.toString());
        	}
        	break;
        case RESULT_TAKE_IMAGE:
        	if(resultCode == RESULT_OK && data != null) {
        		Uri selectedImage = data.getData();
                showMomentCapturedToast();
                onImageSubmit();
        	}
        	break;
        }
        if (is_widget){
            finish();
            System.exit(0);
        }
    }

    public void showMomentCapturedToast() {
        Toast.makeText(this, getString(R.string.inserted), Toast.LENGTH_SHORT).show();
    }
    
    public void onImageSubmit() {
    	
    }
    
    public void createNote(Type type, String text) {
        mDbHelper.createNote(type, text);
    }

	public Data getNote() {
		// Column Indexes:
		// body = 2
		// type = 1
		// id = 0

		Cursor allNotes = mDbHelper.fetchAllNotes();
		int noteCount = allNotes.getCount();

        if (noteCount == 0) {
            return new Data(Type.TEXT, "Add something to your jar to get started!");
        }
		int randIndex = (int) Math.floor(Math.random() * noteCount);
        allNotes.moveToPosition(randIndex);

        Type type;
        String typeAsString = allNotes.getString(1);
        if (typeAsString.equals("text")) {
            type = Type.TEXT;
        } else if (typeAsString.equals("image")) {
            type = Type.IMAGE;
        } else {
            type = Type.LINK;
        }
        String text = allNotes.getString(2);

        return new Data(type, text);
	}
}
