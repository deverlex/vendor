package vn.needy.vendor.screen;

import android.support.v4.app.FragmentActivity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
/**
 * Created by lion on 24/09/2017.
 */

public class BaseFragmentActivity extends FragmentActivity {
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return (keyCode == KeyEvent.KEYCODE_MENU && isMenuWorkaroundRequired()) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && isMenuWorkaroundRequired()) {
            openOptionsMenu();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public static boolean isMenuWorkaroundRequired() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT          &&
                ("LGE".equalsIgnoreCase(Build.MANUFACTURER) || "E6710".equalsIgnoreCase(Build.DEVICE));
    }
}
