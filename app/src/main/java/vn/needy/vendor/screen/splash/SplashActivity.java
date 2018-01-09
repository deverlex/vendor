package vn.needy.vendor.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.domain.Company;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.message.BaseStatus;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.StoreRepository;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.StoreDataLocal;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.store.StoreDataRemote;
import vn.needy.vendor.repository.remote.user.UserDataRemote;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResponse;
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
    private StoreRepository mStoreRepository;
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

        mUserRepository = new UserRepository(
                new UserDataRemote(mVendorApi),
                new UserDataLocal(mPrefsApi)
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(mVendorApi),
                new CompanyDataLocal()
        );
        mStoreRepository = new StoreRepository(
                new StoreDataRemote(mVendorApi),
                new StoreDataLocal()
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
        mUserRepository.getBusinessInformation()
                .subscribeOn(Schedulers.io())
                .doOnError(new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mNavigator.showToastCenterText(error.getMessage());
                        mainPage();
                    }
                })
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseWrapper<BusinessInfoResponse>, ResponseWrapper<BusinessInfoResponse>>() {
                    @Override
                    public ResponseWrapper<BusinessInfoResponse> apply(ResponseWrapper<BusinessInfoResponse> resp) throws Exception {
                        // save company & store response
                        if (resp.getStatus().equals(BaseStatus.OK)) {
                            BusinessInfoResponse data = resp.getData();
                            mCompanyRepository.saveCompanySync(new Company(data.getCompany()));
                            // save store into realm
                            mStoreRepository.saveStoreSync(new Store(data.getStore()));
                        }
                        return resp;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<BusinessInfoResponse>>() {
                    @Override
                    public void accept(ResponseWrapper<BusinessInfoResponse> resp) throws Exception {
                        if (resp.getStatus().equals(BaseStatus.OK)) {
                            mainPage();
                        } else {
                            mNavigator.showToastBottom(resp.getMessage());
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


//        mCompanyRepository.findOurCompany()
//            .subscribeOn(Schedulers.io())
//            .doOnError(new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mNavigator.showToastCenterText(error.getMessage());
//                    mainPage();
//                }
//            })
//            .observeOn(Schedulers.computation())
//            .map(new Function<CompanyResp, CompanyResp>() {
//                @Override
//                public CompanyResp apply(CompanyResp resp) throws Exception {
//                    // save company & store response
//                    if (resp.getCompany() != null) {
//                        mCompanyRepository.saveCompanySync(new Company(resp.getCompany()));
//                    }
//                    return resp;
//                }
//            })
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<CompanyResp>() {
//                @Override
//                public void accept(CompanyResp response) throws Exception {
//                    if (response.getStatus().equals("OK")) {
//                        mainPage();
//                    } else {
//                        mNavigator.showToastBottom(response.getMessage());
//                        registerCompany();
//                    }
//                }
//            }, new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mNavigator.showToastCenterText(error.getMessage());
//                    mainPage();
//                }
//            });
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
        return prefsApi.get(SharedPrefsKey.ACCESS_TOKEN, String.class);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
