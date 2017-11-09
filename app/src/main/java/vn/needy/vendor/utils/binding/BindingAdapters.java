package vn.needy.vendor.utils.binding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import vn.needy.vendor.R;
import vn.needy.vendor.utils.ViewUtil;

/**
 * Created by lion on 02/10/2017.
 */

public class BindingAdapters {

    private static final String TAG = BindingAdapters.class.getName();

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

    @BindingAdapter("srcPath")
    public static void loadImagePath(ImageView imageView, String path) {
        if (!TextUtils.isEmpty(path)) {
            Bitmap bmp = BitmapFactory.decodeFile(path);
            // scale file size, fix out of memory when load more image
            Matrix matrix = new Matrix();
            matrix.setRectToRect(new RectF(0, 0, bmp.getWidth(), bmp.getHeight()),
                    new RectF(0, 0,
                                ViewUtil.dpToPx(imageView.getContext(), 100),
                                ViewUtil.dpToPx(imageView.getContext(), 100)),
                                Matrix.ScaleToFit.CENTER);
            bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
            imageView.setImageBitmap(bmp);
        }
    }

    @BindingAdapter("transformationMethod")
    public static void setTransformationMethod(EditText editText, TransformationMethod method) {
        editText.setTransformationMethod(method);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
                                        LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }
}
