package moments.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextInputDialog extends DialogFragment{
	public static String TAG = "TextInputDialog";

	public static String PROMPT_KEY = "PROMPT_KEY_TEXTINPUTDIALOG";
	
	public interface TextSubmitter {
		void submitText(String string);
	}
	
	private TextSubmitter mTextSubmitter;

	private String prompt;
	private EditText text;

	public static TextInputDialog newInstance(String prompt) {
		Log.d(TAG, "Creating new instance");
		TextInputDialog textInputDialog = new TextInputDialog();

		Bundle args = new Bundle();
		args.putString(PROMPT_KEY, prompt);
		textInputDialog.setArguments(args);

		return textInputDialog;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		Log.d(TAG, "Creating dialog");
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

		try {
			prompt = getArguments().getString(PROMPT_KEY);
			Log.d(TAG, "Has prompt: " + prompt);
		} catch(NullPointerException e){
			prompt = null;
		}


	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    final View view = inflater.inflate(R.layout.text_input_dialog, null);
		text = (EditText) view.findViewById(R.id.text);
		if (prompt != null) {
			text.setHint(prompt);
		}
	    builder.setView(view)
	           .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   EditText happyText = (EditText) view.findViewById(R.id.text);
					   String textToSubmit = happyText.getText().toString();
					   if (QuestionManager.doAppendToBeginning(prompt)) {
						   textToSubmit = prompt + textToSubmit;
					   }
	            	   mTextSubmitter.submitText(textToSubmit);
	               }
	           })
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						TextInputDialog.this.getDialog().cancel();
					}
	           });

		AlertDialog dialog = builder.create();

		final Typeface ralewayFont = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/raleway.ttf");

		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
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
            // Instantiate the NoticeDialogListener so we can send events to the host
            mTextSubmitter = (TextSubmitter) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement TextSubmitter");
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
