package moments.moments;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
import com.parse.Parse;
import com.parse.ParseInstallation;
=======
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
>>>>>>> edb7c81a1f3a06cc9a4e9983c2e910c517697972

import fffb.moments.app.TextInputDialog;


public class MainActivity extends Activity implements TextInputDialog.TextSubmitter, LinkInputDialog.LinkSubmitter{
    
    private int mDataNumber = 1;
    private DataDbAdapter mDbHelper;
    
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_TAKE_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        Parse.initialize(this, "eJQBzNcoqThWmykrIskhwcVgvuZyVFBqztpeecst", "KTM5cp0yA3Tdc6q0lHvNhq7kdqX74Kim2FdRjAOF");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }



=======
        
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
    
>>>>>>> edb7c81a1f3a06cc9a4e9983c2e910c517697972
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
    
    public void openJar(View view) {
    	Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    	view.startAnimation(shake);
    	Toast.makeText(this, "Opening jar", Toast.LENGTH_SHORT).show();
    }
}
