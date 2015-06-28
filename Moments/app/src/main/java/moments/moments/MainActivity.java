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
import com.parse.Parse;
import com.parse.ParseInstallation;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import fffb.moments.app.TextInputDialog;
import fffb.moments.app.LinkInputDialog;

public class MainActivity extends MomentPromptActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, "eJQBzNcoqThWmykrIskhwcVgvuZyVFBqztpeecst",
                "KTM5cp0yA3Tdc6q0lHvNhq7kdqX74Kim2FdRjAOF");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
    
    public void addText(View view) {
    	showPrompt(Type.TEXT, false);
    }

    public void addImage(View view) {
    	showPrompt(Type.IMAGE, false);
    }
    
    public void addLink(View view) {
    	showPrompt(Type.LINK, false);
    }
    
    public void openJar(View view) {
    	Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    	view.startAnimation(shake);
    	Toast.makeText(this, "Opening jar", Toast.LENGTH_SHORT).show();
    }
}
