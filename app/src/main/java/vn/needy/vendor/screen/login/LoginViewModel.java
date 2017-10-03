package vn.needy.vendor.screen.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import vn.needy.vendor.screen.registerAccount.RegisterAccountActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 02/10/2017.
 */

public class LoginViewModel extends BaseObservable implements LoginContract.ViewModel {

    private static final String TAG = LoginViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;

    private String mPhoneNumberError;
    private String mPasswordError;
    private String mPhoneNumber;
    private String mPassword;

    LoginViewModel(Context context, Navigator navigator) {
        mContext = context;
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

    }

    @Override
    public void onLoginSuccess() {

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
    }

    @Override
    public void onSignUpClick() {
        mNavigator.startActivity(RegisterAccountActivity.class);
    }

    @Override
    public void onForgotPasswordClick() {

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