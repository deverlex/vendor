package vn.needy.vendor.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import vn.needy.vendor.data.model.Auth;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsKey;
import vn.needy.vendor.screen.login.LoginActivity;
import vn.needy.vendor.screen.main.MainActivity;
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
        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
        final Auth auth = getAuth(prefsApi);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
//                if (auth == null) {
                    loginPage();
//                } else {
//                    mainPage();
//                }
                new Navigator(SplashActivity.this).startActivity(mIntent);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, SECOND_DELAYED);
    }

    public void loginPage() {
        Log.d(TAG, "loginPage");
        mIntent = new Intent(SplashActivity.this, LoginActivity.class);
    }

    public void mainPage() {
        mIntent = new Intent(SplashActivity.this, MainActivity.class);
    }

    public Auth getAuth(SharedPrefsApi prefsApi) {
        String data = prefsApi.get(SharedPrefsKey.KEY_AUTH, String.class);
        return new Gson().fromJson(data, Auth.class);
    }
}
