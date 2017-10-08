package vn.needy.vendor.utils.binding;

import android.databinding.BindingAdapter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lion on 02/10/2017.
 */

public class BindingUtils {
    private BindingUtils() {
        // No-op
    }

    @BindingAdapter("text")
    public static void setText(TextView view, int resId) {
        view.setText(resId);
    }

    @BindingAdapter("errorText")
    public static void setErrorText(EditText editText, String text) {
        editText.setError(text);
    }
}
