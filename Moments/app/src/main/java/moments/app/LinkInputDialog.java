package moments.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LinkInputDialog extends DialogFragment{
	
	public interface LinkSubmitter {
		public void submitLink(String string);
	}
	
	private LinkSubmitter mLinkSubmitter;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    final View view = inflater.inflate(R.layout.link_input_dialog, null);
	    builder.setView(view)
	           .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   EditText happyText = (EditText) view.findViewById(R.id.link);
	            	   mLinkSubmitter.submitLink(happyText.getText().toString());
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   LinkInputDialog.this.getDialog().cancel();
				   }
			   });

		AlertDialog dialog = builder.create();

		final Typeface ralewayFont = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/raleway.ttf");

		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {                    //
				Button positiveButton = ((AlertDialog) dialog)
						.getButton(AlertDialog.BUTTON_POSITIVE);
				positiveButton.setTypeface(ralewayFont);

				Button negativeButton = ((AlertDialog) dialog)
						.getButton(AlertDialog.BUTTON_NEGATIVE);
				negativeButton.setTypeface(ralewayFont);
			}
		});
		return dialog;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
            mLinkSubmitter = (LinkSubmitter) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LinkSubmitter");
        }
	}

	@Override
	public void onDismiss(final DialogInterface dialog) {
		super.onDismiss(dialog);
		final Activity activity = getActivity();
		if (activity instanceof DialogInterface.OnDismissListener) {
			((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
		}
	}
}
