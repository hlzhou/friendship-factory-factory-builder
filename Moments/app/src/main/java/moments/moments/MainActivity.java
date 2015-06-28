package moments.moments;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements TextInputDialog.TextSubmitter{

    private ImageButton mAddText;
    private ImageButton mAddImage;
    private ImageButton mAddLink;

    private int mDataNumber = 1;
    private DataDbAdapter mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddText = (ImageButton) findViewById(R.id.add_text);
        mAddImage = (ImageButton) findViewById(R.id.add_image);
        mAddLink = (ImageButton) findViewById(R.id.add_link);
        mDbHelper = new DataDbAdapter(this);
        mDbHelper.open();
    }

    public void addText(View view) {
        new TextInputDialog().show(getFragmentManager(), "textInput");
    }

    public void submitText(String text, Type type) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        createNote(type, text);
    }

    public void addImage(View view) {

    }

    public void addLink(View view) {

    }

    private void createNote(Type type, String text) {
        String dataName = "Note " + mDataNumber++;
        mDbHelper.createNote(Type.TEXT, "test");
    }
}
