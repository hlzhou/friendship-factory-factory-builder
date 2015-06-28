package moments.moments;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import fffb.moments.app.TextInputDialog;


public class MainActivity extends Activity implements TextInputDialog.TextSubmitter, LinkInputDialog.LinkSubmitter{
    
    private int mDataNumber = 1;
    private DataDbAdapter mDbHelper;
    
    private static int RESULT_LOAD_IMAGE = 1;

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
    	Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            createNote(Type.IMAGE, selectedImage.toString());
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
