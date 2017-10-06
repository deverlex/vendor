package vn.needy.vendor.screen.login;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.screen.forgotPassword.ForgotPasswordActivity;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.registerAccount.RegisterAccountActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 02/10/2017.
 */

public class LoginViewModel extends BaseObservable implements LoginContract.ViewModel {

    private static final String TAG = LoginViewModel.class.getName();

    private final Context mContext;
    private final Application mApplication;
    private final Navigator mNavigator;
    private LoginContract.Presenter mPresenter;

    private String mPhoneNumberError;
    private String mPasswordError;
    private String mPhoneNumber;
    private String mPassword;

    LoginViewModel(Context context, Application application, Navigator navigator) {
        mContext = context;
        mApplication = application;
        mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoginSuccess() {

        VendorServiceClient.initialize(mApplication);
    }

    @Override
    public void onInputPhoneNumberError() {

    }

    @Override
    public void onInputPasswordError() {

    }

    @Override
    public void onLoginClick() {
        Log.d(TAG, "onLoginClick()");
//        mNavigator.startActivity(MainActivity.class);
//        mNavigator.finishActivity();
        mPresenter.login(mPhoneNumber, mPassword);
    }

    @Override
    public void onLoginError(BaseException exception) {
//        mNavigator.showToastCustom(exeption.getMessage());
    }

    @Override
    public void onSignUpClick() {
        mNavigator.startActivity(RegisterAccountActivity.class);
    }

    @Override
    public void onForgotPasswordClick() {
        mNavigator.startActivity(ForgotPasswordActivity.class);
    }

    @Override
    public void onShowProgressBar() {

    }

    @Override
    public void onHideProgressBar() {

    }

    @Bindable
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    @Bindable
    public String getPhoneNumberError() {
        return mPhoneNumberError;
    }

    @Bindable
    public String getPasswordError() {
        return mPasswordError;
    }
}
