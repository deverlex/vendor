package vn.needy.vendor.utils.binding;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

    /**
     * setAdapter For RecyclerView
     */
    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
                                                 RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("text")
    public static void setText(TextView view, int resId) {
        view.setText(resId);
    }

    @BindingAdapter("errorText")
    public static void setErrorText(EditText editText, String text) {
        editText.setError(text);
    }

    @BindingAdapter("htmlText")
    public static void setHtmlText(TextView textView, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(text));
        }
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
