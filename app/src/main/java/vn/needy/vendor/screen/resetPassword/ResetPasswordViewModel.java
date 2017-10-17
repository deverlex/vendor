package vn.needy.vendor.screen.resetPassword;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.screen.forgotPassword.ForgotPasswordActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 04/10/2017.
 */

public class ResetPasswordViewModel extends BaseObservable implements ResetPasswordContract.ViewModel {

    private static final String TAG = ResetPasswordViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;

    private ResetPasswordContract.Presenter mPresenter;

    private String mPasswordError;
    private String mPassword;

    public ResetPasswordViewModel(Context context, Navigator navigator, DialogManager dialogManager) {
        mContext = context;
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

