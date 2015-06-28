package fffb.moments.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import moments.moments.R;
import moments.moments.Type;

public class TextInputDialog extends DialogFragment{
	
	public interface TextSubmitter {
		public void submitText(String string, Type type);
	}
	
	private TextSubmitter mTextSubmitter;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    final View view = inflater.inflate(R.layout.text_input_dialog, null);
	    builder.setView(view)
	    // Add action buttons
	           .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   EditText happyText = (EditText) view.findViewById(R.id.text);
	            	   mTextSubmitter.submitText(happyText.getText().toString(), Type.TEXT);
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   TextInputDialog.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
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
}