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


public class MainActivity extends MomentPromptActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void addText(View view) {
    	showPrompt(this, Type.TEXT);
    }
    
    public void addImage(View view) {
    	showPrompt(this, Type.IMAGE);    	
    }
    
    public void addLink(View view) {
    	showPrompt(this, Type.LINK);
    }
    
    public void openJar(View view) {
    	Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    	view.startAnimation(shake);
    	Toast.makeText(this, "Opening jar", Toast.LENGTH_SHORT).show();
    }
}
