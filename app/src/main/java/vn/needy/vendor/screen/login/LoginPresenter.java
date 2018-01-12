package vn.needy.vendor.screen.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.domain.Company;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.domain.User;
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
import vn.needy.vendor.repository.remote.user.request.LoginRequest;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResponse;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.repository.remote.user.response.LoginResponse;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.utils.Constant;
import vn.needy.vendor.utils.Utils;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getName();

    private final LoginContract.ViewModel mViewModel;
    private Context mContext;

    private Navigator mNavigator;
    private UserRepository mUserRepository;

    private CompanyRepository mCompanyRepository;
    private StoreRepository mStoreRepository;

    LoginPresenter(Context context, LoginContract.ViewModel viewModel, Navigator navigator) {
        mContext = context;
        mViewModel = viewModel;
        mNavigator = navigator;
        mUserRepository = new UserRepository(
                new UserDataRemote(VendorServiceClient.getInstance()),
                new UserDataLocal(SharedPrefsImpl.getInstance())
        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void login(final String phoneNumber, final String passWord) {
        if (!validateDataInput(phoneNumber, passWord)) {
            return;
        }
        final LoginRequest request = new LoginRequest(phoneNumber, passWord);
        request.setScope(Constant.SCOPE);
        request.setInstanceId(Utils.getDeviceId(mContext));
        request.setFirebaseToken(FirebaseInstanceId.getInstance().getToken());
        mUserRepository.login(request)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    mViewModel.onShowProgressBar();
                }
            })
            .doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mNavigator.showToastCenterText(error.getMessage());
                }
            }).observeOn(Schedulers.computation())
                .map(new Function<ResponseWrapper<TokenResponse>, ResponseWrapper<TokenResponse>>() {
                    @Override
                    public ResponseWrapper<TokenResponse> apply(ResponseWrapper<TokenResponse> tokenResponseResponseWrapper) throws Exception {
                        TokenResponse data = tokenResponseResponseWrapper.getData();
                        if (data != null && data.getTokenAccess() != null) {
                            mUserRepository.saveAccessTokenSync(data.getTokenAccess());
                            mUserRepository.saveRefreshTokenSync(data.getRefreshToken());
                            mUserRepository.saveExpiresIn(data.getExpiresIn());

                        }
                        return tokenResponseResponseWrapper;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<TokenResponse>>() {
                    @Override
                    public void accept(ResponseWrapper<TokenResponse> tokenResponseResponseWrapper) throws Exception {
                        mViewModel.onHideProgressBar();
                        Log.d(TAG, tokenResponseResponseWrapper.getStatus());
                        if (tokenResponseResponseWrapper.getStatus().equals(BaseStatus.ERROR)) {
                            mViewModel.onLoginError(tokenResponseResponseWrapper.getMessage());
                        } else {
                            mViewModel.onLoginSuccess();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onHideProgressBar();
                        Log.d(TAG, error.getMessage());
                        mViewModel.onLoginError(R.string.error_credential);
                    }
                });
    }

    @Override
    public boolean validateDataInput(String phoneNumber, String passWord) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.phone_number_empty);
        }
        if (!Utils.PhoneNumberUtils.isValidPhoneNumber(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.is_not_phone_number);
        }
        if (TextUtils.isEmpty(passWord)) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_empty);
        }
        if (!TextUtils.isEmpty(passWord) && passWord.length() > 32) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_over_limit);
        }
        if (!TextUtils.isEmpty(passWord) && passWord.length() < 8) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_miss_length);
        }
        return isValidate;
    }

    @Override
    public void findCompany() {
        updateRepositoryAfterResetApi();
        // check company
        mUserRepository.checkOwnCompanyExist()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mViewModel.onHideProgressBar();
                    }
                })
                .doOnError(new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onLoginError(error.getMessage());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper>() {
                    @Override
                    public void accept(ResponseWrapper responseWrapper) throws Exception {
                        if (responseWrapper.getStatus().equals(BaseStatus.OK)) {
                            mViewModel.onToMainPage();
                        } else {
                            mViewModel.onToRegisterCompany();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mNavigator.showToastCenterText(error.getMessage());
                        mViewModel.onLoginError(error.getMessage());
                    }
                });
    }

    private void updateRepositoryAfterResetApi() {
        // update API
        mUserRepository = new UserRepository(
                new UserDataRemote(VendorServiceClient.getInstance()),
                new UserDataLocal(SharedPrefsImpl.getInstance())
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(VendorServiceClient.getInstance()),
                new CompanyDataLocal()
        );
        mStoreRepository = new StoreRepository(
                new StoreDataRemote(VendorServiceClient.getInstance()),
                new StoreDataLocal()
        );
    }
}
