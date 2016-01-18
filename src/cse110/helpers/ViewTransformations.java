/**
 * 
 */
package cse110.helpers;

import cse110.tacode.R;
import android.widget.EditText;

/**
 * This class holds static functions for doing visual transformations on view elements
 * @author Casey
 */
public class ViewTransformations {
	/**
	 * Highlights the TextView with a red border
	 * @param t TextView to be highlighted
	 */
	public static void highlightTextViewBorder(EditText t) {
		t.setBackgroundResource(R.drawable.error_edit_text);
	}
	
	/**
	 * Restores EditText to default
	 * @param t EditText view to reset
	 */
	public static void restoreEditTextBackground(EditText t) {
		t.setBackgroundResource(android.R.drawable.edit_text);
	}
}

