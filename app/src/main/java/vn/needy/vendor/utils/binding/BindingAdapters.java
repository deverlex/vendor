package vn.needy.vendor.utils.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.text.method.TransformationMethod;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * Created by lion on 02/10/2017.
 */

public class BindingAdapters {
    private BindingAdapters() {
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

    @BindingAdapter("srcVector")
    public static void setSrcVector(ImageButton view, @DrawableRes int drawable) {
        view.setImageResource(drawable);
    }

    @BindingAdapter("transformationMethod")
    public static void setTransformationMethod(EditText editText, TransformationMethod method) {
        editText.setTransformationMethod(method);
    }
}
