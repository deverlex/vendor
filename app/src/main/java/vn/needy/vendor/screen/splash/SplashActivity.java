package vn.needy.vendor.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.user.UserDataRemote;
import vn.needy.vendor.repository.remote.user.response.BusinessIdResponse;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.screen.login.LoginActivity;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.registerCompany.RegisterCompanyActivity;
import vn.needy.vendor.utils.navigator.Navigator;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();

    private static final int SECOND_DELAYED = 500;
    private Handler mHandler;
    private Runnable mRunnable;
    private Intent mIntent;

    private UserRepository mUserRepository;
    private CompanyRepository mCompanyRepository;
    private Navigator mNavigator;

    private SharedPrefsApi mPrefsApi;
    private VendorApi mVendorApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setContentView(R.layout.activity_splash);

        mPrefsApi = SharedPrefsImpl.getInstance();
        mVendorApi = VendorServiceClient.getInstance();

        // create new user data source
        mUserRepository = new UserRepository(
                new UserDataRemote(mVendorApi),
                new UserDataLocal(mPrefsApi)
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(mVendorApi),
                new CompanyDataLocal()
        );

        final String token = getToken(mPrefsApi);

        mNavigator = new Navigator(this);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
//                if (TextUtils.isEmpty(token)) {
                    loginPage();
//                } else {
//                    gateway();
//                }
//                new Navigator(SplashActivity.this).startActivity(mIntent);
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
        mUserRepository.findUserBusinessId()
            .subscribeOn(Schedulers.io())
            .doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mNavigator.showToastCenterText(error.getMessage());
                    mainPage();
                }
            })
            .doOnNext(new Consumer<BusinessIdResponse>() {
                @Override
                public void accept(BusinessIdResponse response) throws Exception {
                    // save company & store response
                    Log.w(TAG, "company_id: " + response.getCompanyId()
                            + ", store_id: " + response.getStoreId());
                    
//                    mUserRepository.saveCompanyId(response.getCompanyId());
//                    mUserRepository.saveStoreId(response.getStoreId());
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<BusinessIdResponse>() {
                @Override
                public void accept(BusinessIdResponse response) throws Exception {
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

    // We will getAsync it and refresh, if fail -> re-login
    private String getToken(SharedPrefsApi prefsApi) {
        return prefsApi.get(SharedPrefsKey.TOKEN_KEY, String.class);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
