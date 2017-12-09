package vn.needy.vendor.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.user.UserDataSource;
import vn.needy.vendor.api.v1.user.UserDataSourceImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.screen.login.LoginActivity;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.registerCompany.RegisterCompanyActivity;
import vn.needy.vendor.utils.navigator.Navigator;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();

    private static final int SECOND_DELAYED = 1000;
    private Handler mHandler;
    private Runnable mRunnable;
    private Intent mIntent;

    private UserDataSource mUserDataSource;
    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
        // create new user data source
        mUserDataSource = new UserDataSourceImpl(prefsApi);

        final String token = getToken(prefsApi);

        mNavigator = new Navigator(this);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
//                if (TextUtils.isEmpty(token)) {
//                    loginPage();
//                } else {
                    gateway();
//                }
//                new Navigator(SplashActivity.this).startActivity(mIntent);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, SECOND_DELAYED);
    }

    // If app is signed then check user has company
    // -> save company info and redirect to main
    // -> go to register company
    private void gateway() {
        // will check each login app because user maybe remove by ceo/manager
        // if connect has error, redirect to main page
        mUserDataSource.findUserCompany()
            .subscribeOn(Schedulers.io())
            .doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mNavigator.showToastCenterText(error.getMessage());
                    mainPage();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<BaseResponse>() {
                @Override
                public void accept(BaseResponse response) throws Exception {
                    if (response.getStatus().equals("OK")) {
                        mainPage();
                    } else {
                        mNavigator.showToastBottom(response.getMessage());
                        registerCompany();
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mNavigator.showToastCenterText(error.getMessage());
                    mainPage();
                }
            });
    }

    public void loginPage() {
        mIntent = new Intent(this, LoginActivity.class);
        mNavigator.startActivity(mIntent);
        mNavigator.finishActivity();
    }

    private void registerCompany() {
        mIntent = new Intent(this, RegisterCompanyActivity.class);
        mNavigator.startActivity(mIntent);
        mNavigator.finishActivity();
    }

    public void mainPage() {
        mIntent = new Intent(this, MainActivity.class);
        mNavigator.startActivity(mIntent);
        mNavigator.finishActivity();
    }

    // We will get it and refresh, if fail -> re-login
    private String getToken(SharedPrefsApi prefsApi) {
        return prefsApi.get(SharedPrefsKey.TOKEN_KEY, String.class);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
