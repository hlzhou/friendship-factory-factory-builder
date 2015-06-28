package moments.moments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import fffb.moments.app.TextInputDialog;
import fffb.moments.app.LinkInputDialog;

public class MainActivity extends Activity implements TextInputDialog.TextSubmitter, LinkInputDialog.LinkSubmitter{
    
    private int mDataNumber = 1;
    private DataDbAdapter mDbHelper;
    
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_TAKE_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mDbHelper = new DataDbAdapter(this);
        mDbHelper.open();
    }
    
    public void addText(View view) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.show(getFragmentManager(), "textInput");
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
    
    public void addImage(View view) {
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
    
    public void addLink(View view) {
    	LinkInputDialog dialog = new LinkInputDialog();
    	dialog.show(getFragmentManager(), "linkInput");
    }
    
    private void createNote(Type type, String text) {
        String dataName = "Note " + mDataNumber++;
        mDbHelper.createNote(Type.TEXT, "test");
    }
}
