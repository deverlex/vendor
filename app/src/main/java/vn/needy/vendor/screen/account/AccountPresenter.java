package vn.needy.vendor.screen.account;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.message.BaseCode;
import vn.needy.vendor.port.message.ResponseWrapper;
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

/**
 * Created by lion on 05/12/2017.
 */

public class AccountPresenter implements AccountContract.Presenter {

    private Context mContext;
    private AccountContract.ViewModel mViewModel;
    private UserRepository mUserRepository;
    private CompanyRepository mCompanyRepository;
    private StoreRepository mStoreRepository;
    private SharedPrefsApi mPrefsApi;

    public AccountPresenter(Context mContext, AccountContract.ViewModel viewModel) {
        this.mContext = mContext;
        mViewModel = viewModel;
        mUserRepository = new UserRepository(
                new UserDataRemote(VendorServiceClient.getInstance()),
                new UserDataLocal(SharedPrefsImpl.getInstance())
        );

        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(VendorServiceClient.getInstance()),
                new CompanyDataLocal(SharedPrefsImpl.getInstance())
        );

        mStoreRepository = new StoreRepository(
                new StoreDataRemote(VendorServiceClient.getInstance()),
                new StoreDataLocal(SharedPrefsImpl.getInstance())
        );

        mPrefsApi = SharedPrefsImpl.getInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void logout() {
        mUserRepository.logout(mPrefsApi.get(SharedPrefsKey.REFRESH_TOKEN, String.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper>() {
                    @Override
                    public void accept(ResponseWrapper responseWrapper) throws Exception {
                        if (responseWrapper.getCode() == BaseCode.OK) {
                            SharedPrefsImpl.getInstance().clear();
                            mViewModel.goToLogin();
                        } else {
                            mViewModel.onLogoutError(R.string.logout_error);
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onLogoutError(R.string.logout_error);
                    }
                });
    }
}
