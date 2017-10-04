package vn.needy.vendor.screen.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.ViewUtil;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();

    private BottomBar mBottomBar;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mBottomBar = ViewUtil.findById(this, R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Log.d(TAG, "onTabSelected: " + tabId);

                switch (tabId) {
                    case R.id.store:
                        break;
                    case R.id.cart:
                        break;
                    case R.id.notification:
                        break;
                    case R.id.account:
                        break;
                    default:

                }
            }
        });
    }


}
