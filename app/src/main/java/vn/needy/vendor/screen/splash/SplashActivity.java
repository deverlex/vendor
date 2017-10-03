package vn.needy.vendor.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import vn.needy.vendor.screen.login.LoginActivity;
import vn.needy.vendor.utils.navigator.Navigator;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();

    private static final int SECOND_DELAYED = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginPage();

        Navigator navigator = new Navigator(SplashActivity.this);
        navigator.startActivity(mIntent);
        navigator.finishActivity();
    }

    public void loginPage() {
        Log.d(TAG, "loginPage");
        mIntent = new Intent(SplashActivity.this, LoginActivity.class);
    }
}
