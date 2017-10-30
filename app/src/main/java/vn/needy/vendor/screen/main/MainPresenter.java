package vn.needy.vendor.screen.main;

import android.util.Log;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.model.UserState;
import vn.needy.vendor.data.source.CompanyRepository;
import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.CompanyLocalDataSource;
import vn.needy.vendor.data.source.local.UserLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.CompanyRemoteDataSource;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.response.UserResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;

/**
 * Created by lion on 30/10/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();

    private final CompositeDisposable mCompositeDisposable;

    private final UserRepository mUserRepository;
    private final CompanyRepository mCompanyRepository;
    private final MainContract.RequireComplete mRequireComplete;

    public MainPresenter(MainContract.RequireComplete requireComplete, RealmApi realmApi) {
        mRequireComplete = requireComplete;
        mUserRepository = new UserRepository(
                new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(realmApi, SharedPrefsImpl.getInstance())
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(realmApi)
        );
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void loadUserInformation() {
        Disposable disposable = mUserRepository.getUserInformation()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserResponse>() {
                    @Override
                    public void accept(UserResponse userResponse) throws Exception {
                        User user = userResponse.getUser();
                        Log.d(TAG, "user? " + user.getState());
                        if (user != null && user.getState() == UserState.ACTIVE) {
                            mRequireComplete.onLoadUserInfoComplete(true);
                        } else {
                            mRequireComplete.onLoadUserInfoComplete(false);
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadCompanyInformation() {
        Disposable disposable = mCompanyRepository.getCompanyInformation()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CompanyResponse>() {
                    @Override
                    public void accept(CompanyResponse companyResponse) throws Exception {
                        Company company = companyResponse.getCompany();
                        if (company != null) {
                            mRequireComplete.onRequireCompleteInfo(true);
                        } else mRequireComplete.onRequireCompleteInfo(false);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadCartList() {

    }

    @Override
    public void loadNotifications() {

    }
}
