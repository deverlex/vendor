package vn.needy.vendor.screen.login;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.datasource.BaseResponse;
import vn.needy.vendor.datasource.authentication.AuthenticationDataSource;
import vn.needy.vendor.datasource.authentication.AuthenticationDataSourceImpl;
import vn.needy.vendor.datasource.user.UserDataSource;
import vn.needy.vendor.datasource.user.UserDataSourceImpl;
import vn.needy.vendor.service.sharedprf.SharedPrefsApi;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.datasource.authentication.response.TokenResponse;
import vn.needy.vendor.utils.Utils;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getName();

    private final LoginContract.ViewModel mViewModel;

    private Navigator mNavigator;
    private final AuthenticationDataSource mAuthDataSource;
    private UserDataSource mUserDataSource;
    private SharedPrefsApi mPrefsApi;

    LoginPresenter(LoginContract.ViewModel viewModel, Navigator navigator, SharedPrefsApi prefsApi) {
        mViewModel = viewModel;
        mNavigator = navigator;
        mPrefsApi = prefsApi;
        mAuthDataSource = new AuthenticationDataSourceImpl(prefsApi);
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
        mAuthDataSource.login(phoneNumber, passWord)
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
            })
            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<TokenResponse>() {
                @Override
                public void accept(TokenResponse certification) throws Exception {
                    mViewModel.onHideProgressBar();
                    String token = certification.getToken();
                    // Save token into storage
                    if (TextUtils.isEmpty(token)) {
                        mViewModel.onLoginError(certification.getMessage());
                    }  else {
                        mAuthDataSource.saveToken(token);
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
//        String deviceToken = FirebaseInstanceId.getInstance().getToken();
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
        mUserDataSource = new UserDataSourceImpl(mPrefsApi);
        mUserDataSource.findUserCompany()
            .subscribeOn(Schedulers.io())
            .doAfterTerminate(new Action() {
                @Override
                public void run() throws Exception {
                    mViewModel.onHideProgressBar();
                }
            }).doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {

                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<BaseResponse>() {
                @Override
                public void accept(BaseResponse response) throws Exception {
                    if (response.getStatus().equals("OK")) {
                        mViewModel.onToMainPage();
                    } else {
                        mNavigator.showToastBottom(response.getMessage());
                        mViewModel.onToRegisterCompany();
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onLoginError(error.getMessage());
                }
            });
    }
}
