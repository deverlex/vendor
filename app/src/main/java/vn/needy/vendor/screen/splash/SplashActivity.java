package vn.needy.vendor.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.api.v1.company.CompanyRepository;
import vn.needy.vendor.api.v1.company.CompanyLocalDataSource;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.api.v1.company.CompanyRemoteDataSource;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;
import vn.needy.vendor.service.VendorServiceClient;
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

    private RealmApi mRealmApi;

    private CompositeDisposable mCompositeDisposable;
    private CompanyRepository mCompanyRepository;
    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mCompositeDisposable = new CompositeDisposable();
        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();

        mRealmApi = new RealmApi();
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(SharedPrefsImpl.getInstance())
        );

        final String token = getToken(prefsApi);

        mNavigator = new Navigator(this);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
//                if (TextUtils.isEmpty(token)) {
//                    loginPage();
//                } else {
                    gatewaySigned();
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
    private void gatewaySigned() {
        // will check each login app because user maybe remove by ceo/manager
        // if connect has error, redirect to main page
        Disposable disposable = mCompanyRepository.getCompanyInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CompanyResponse>() {
                    @Override
                    public void accept(CompanyResponse companyResponse) throws Exception {
                        if (companyResponse.getCompany() != null) {
                            mainPage();
                        } else {
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
        mCompositeDisposable.add(disposable);
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
        mRealmApi.closeRealmOnMainThread();
    }
}
