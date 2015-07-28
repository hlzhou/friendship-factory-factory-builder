package moments.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import moments.app.database.Data;
import moments.app.database.DataDbAdapter;

public class MomentPromptActivity extends Activity implements TextInputDialog.TextSubmitter, LinkInputDialog.LinkSubmitter, DialogInterface.OnDismissListener{
    private static final String TAG = "MomentPromptActivity";
	public int mDataNumber = 1;
    public DataDbAdapter mDbHelper;

    public static final int RESULT_LOAD_IMAGE = 1;
    public static final int RESULT_TAKE_IMAGE = 2;

    public void showTextWithPrompt(String prompt) {
        TextInputDialog textInputDialog = TextInputDialog.newInstance(prompt);
        textInputDialog.show(getFragmentManager(), "textInput");
    }

	public void showPrompt(Type type) {
        switch(type){
		case TEXT:
			TextInputDialog tDialog = new TextInputDialog();
	    	tDialog.show(getFragmentManager(), "textInput");
	    	break;
		case IMAGE:
			//openImageSourcePickerDialog();
            openGetImageFromGallery();
	    	break;
		case LINK:
			LinkInputDialog lDialog = new LinkInputDialog();
	    	lDialog.show(getFragmentManager(), "linkInput");
	    	break;
		}
	}

    private void openImageSourcePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.image_source_prompt)
                .setItems(R.array.image_sources, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch(which){
                            case 0:
                                openTakePicture();
                                break;
                            case 1:
                                openGetImageFromGallery();
                                break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(this);
        dialog.show();
    }

    private void openTakePicture() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, RESULT_TAKE_IMAGE);
    }

    private void openGetImageFromGallery() {
        Intent getImageFromGallery = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(getImageFromGallery, RESULT_LOAD_IMAGE);
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
        onSubmit();
    }
    
    @Override
	public void submitLink(String text) {
        showMomentCapturedToast();
    	createNote(Type.LINK, text);
        onSubmit();
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
        case RESULT_LOAD_IMAGE:
            Log.d(TAG, "On Activity Result - RESULT_LOAD_IMAGE");
            Log.d(TAG, "data: " + data);
        	if(resultCode == RESULT_OK && data != null) {
                Log.d(TAG, "Proceeding with submiting image");
        		Uri selectedImage = data.getData();
                showMomentCapturedToast();
                createNote(Type.IMAGE, selectedImage.toString());
                onImageSubmit();
            }else {
                onDismiss(null);
            }
        	break;
        case RESULT_TAKE_IMAGE:
        	if(resultCode == RESULT_OK && data != null) {
        		Uri selectedImage = data.getData();
                showMomentCapturedToast();
                onImageSubmit();
        	}else {
                onDismiss(null);
            }
        	break;
        }
    }

    public void showMomentCapturedToast() {
        Toast.makeText(this, getString(R.string.inserted), Toast.LENGTH_SHORT).show();
    }
    
    protected void onImageSubmit() {
    	onSubmit();
    }

    /**
     * Called when a moment is submitted
     */
    protected void onSubmit() {

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

    /**
     * Called when the input dialog is dismissed without entering any moment
     */
    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }
}