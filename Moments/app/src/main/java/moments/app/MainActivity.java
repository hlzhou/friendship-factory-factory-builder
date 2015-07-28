package moments.app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import moments.app.notification.HappyNotificationManager;

public class MainActivity extends MomentPromptActivity {
    
    private MediaPlayer shakeNoise;
	private MediaPlayer ripNoise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton addText = (ImageButton)findViewById(R.id.add_text);

        ImageButton addImage = (ImageButton)findViewById(R.id.add_image);
        addImage.setMinimumHeight(addImage.getMeasuredWidth());
        ImageButton addLink = (ImageButton)findViewById(R.id.add_link);
        addLink.setMinimumHeight(addLink.getMeasuredWidth());

        if(addText.getViewTreeObserver().isAlive()){
            addText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    addText.setMinimumHeight(addText.getMeasuredWidth());
                }
            });
        }

        shakeNoise = MediaPlayer.create(this, R.raw.shake);
        ripNoise = MediaPlayer.create(this, R.raw.paperripping);
        HappyNotificationManager.startDailyNotification(getApplicationContext());
    }

    public void addText(View view) {
    	showPrompt(Type.TEXT);
    }

    public void addImage(View view) {
    	showPrompt(Type.IMAGE);
    }
    
    public void addLink(View view) {
    	showPrompt(Type.LINK);
    }
    
    @Override
    public void submitText(String text){
    	super.submitText(text);
    	playInsertNoise();
    }
    
    @Override
    public void submitLink(String text) {
    	super.submitLink(text);
    	playInsertNoise();
    }
    
    @Override
    protected void onImageSubmit() {
    	super.onImageSubmit();
    	playInsertNoise();
    }
    
    public void openJar(View view) {
    	Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    	view.startAnimation(shake);
    	Toast.makeText(this, "Opening jar", Toast.LENGTH_SHORT).show();
    	playOpenJarNoise();
        MomentDisplayer.showMoment(this, getNote());
    }

    public void openSettings(View view) {
        Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(settings);
    }

    public void playInsertNoise() {
    	ripNoise.start();
    }
    
    public void playOpenJarNoise() {
    	shakeNoise.start();
    }
}
