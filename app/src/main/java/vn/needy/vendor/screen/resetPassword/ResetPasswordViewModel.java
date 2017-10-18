package vn.needy.vendor.screen.resetPassword;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.screen.forgotPassword.ForgotPasswordActivity;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.registerCompany.RegisterCompanyActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 04/10/2017.
 */

public class ResetPasswordViewModel extends BaseObservable implements ResetPasswordContract.ViewModel {

    private static final String TAG = ResetPasswordViewModel.class.getName();

    private final Context mContext;
    private final Application mApplication;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;

    private ResetPasswordContract.Presenter mPresenter;

    private String mPasswordError;
    private String mPassword;

    public ResetPasswordViewModel(Context context, Application application, Navigator navigator, DialogManager dialogManager) {
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
    public void setPresenter(ResetPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputPasswordError(int errorMsg) {
        mPasswordError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.passwordError);
    }

    @Override
    public void onClickResetPassword() {
        Bundle extras = ((Activity) mContext).getIntent().getExtras();
        String phoneNumber = extras.getString(ForgotPasswordActivity.KEY_PHONE_NUMBER);
        String firebaseToken = extras.getString(ForgotPasswordActivity.KEY_FIREBASE_TOKEN);
        mPresenter.resetPassword(phoneNumber, firebaseToken, mPassword);
    }

    @Override
    public void onResetPasswordError(String message) {
        Log.w(TAG, "onResetPasswordError(String message)");
    }

    @Override
    public void onResetPasswordSuccess() {
        VendorServiceClient.initialize(mApplication);
        mPresenter.findCompanyInherent();
    }

    @Override
    public void onShowProgressBar() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onRedirectToMain() {
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }

    @Override
    public void onRedirectToRegisterCompany() {
        mNavigator.startActivity(RegisterCompanyActivity.class);
        mNavigator.finishActivity();
    }

    @Bindable
    public String getPasswordError() {
        return mPasswordError;
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}

