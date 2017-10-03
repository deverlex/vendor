package vn.needy.vendor.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by lion on 02/10/2017.
 */

public class ViewUtil {
    @SuppressWarnings("unchecked")
    public static <T extends View> T findById(@NonNull View parent, @IdRes int resId) {
        return (T) parent.findViewById(resId);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findById(@NonNull Activity parent, @IdRes int resId) {
        return (T) parent.findViewById(resId);
    }

    public static int dpToPx(Context context, int dp) {
        return (int)((dp * context.getResources().getDisplayMetrics().density) + 0.5);
    }
}
