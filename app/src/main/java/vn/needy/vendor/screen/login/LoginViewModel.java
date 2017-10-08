package vn.needy.vendor.screen.login;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.screen.forgotPassword.ForgotPasswordActivity;
import vn.needy.vendor.screen.registerAccount.RegisterAccountActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
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
    private final DialogManager mDialogManager;

    private String mPhoneNumberError;
    private String mPasswordError;
    private String mPhoneNumber;
    private String mPassword;

    LoginViewModel(Context context, Application application,
                   Navigator navigator, DialogManager dialogManager) {
        mContext = context;
        mApplication = application;
        mNavigator = navigator;
        mDialogManager = dialogManager;
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
        ((LoginActivity) mContext).onGateway();
    }

    @Override
    public void onInputPhoneNumberError(int errorMsg) {
        mPhoneNumberError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.phoneNumberError);
    }

    @Override
    public void onInputPasswordError(int errorMsg) {
        mPasswordError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.passwordError);
    }

    @Override
    public void onLoginClick() {
        mPresenter.login(mPhoneNumber, mPassword);
    }

    @Override
    public void onLoginError(BaseException exception) {
        mNavigator.showToastCustom(exception.getMessage());
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
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
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

    public interface Gateway {
        void onGateway();
    }
}
