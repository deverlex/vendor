package vn.needy.vendor.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.CompanyRepository;
import vn.needy.vendor.data.source.local.CompanyLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsKey;
import vn.needy.vendor.data.source.remote.CompanyRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.screen.login.LoginActivity;
import vn.needy.vendor.screen.main.MainActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mCompositeDisposable = new CompositeDisposable();
        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();

        mRealmApi = new RealmApi();
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(mRealmApi)
        );

        final String token = getToken(prefsApi);

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
//                if (TextUtils.isEmpty(token)) {
//                    loginPage();
//                } else {
                    mainPage();
//                }
                new Navigator(SplashActivity.this).startActivity(mIntent);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, SECOND_DELAYED);
    }

    public void loginPage() {
        mIntent = new Intent(SplashActivity.this, LoginActivity.class);
    }

    public void mainPage() {
        Disposable disposable = mCompanyRepository.getCompanyInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CompanyResponse>() {
                    @Override
                    public void accept(CompanyResponse companyResponse) throws Exception {
                        Log.w(TAG, companyResponse.getCompany().getName());
                        saveCompany(companyResponse.getCompany());
                    }
                });
        mCompositeDisposable.add(disposable);
        mIntent = new Intent(SplashActivity.this, MainActivity.class);
    }

    private void saveCompany(Company company) {
        mCompanyRepository.saveCompany(company);
    }

    // We will get it and refresh, if fail -> relogin
    private String getToken(SharedPrefsApi prefsApi) {
        return prefsApi.get(SharedPrefsKey.TOKEN_KEY, String.class);
    }

    private boolean getCompany() {
        mRealmApi.realmGet(new BiConsumer<ObservableEmitter<? super Object>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Object> observableEmitter, Realm realm) throws Exception {

            }
        });
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        mRealmApi.closeRealmOnMainThread();
    }
}
