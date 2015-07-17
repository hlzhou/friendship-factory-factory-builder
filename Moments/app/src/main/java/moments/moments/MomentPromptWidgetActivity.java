package moments.moments;

import android.content.DialogInterface;

public class MomentPromptWidgetActivity extends MomentPromptActivity {
    @Override
    protected void onSubmit() {
        super.onSubmit();
        finish();
        System.exit(0);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        finish();
        System.exit(0);
    }
}
