package vn.needy.vendor.screen.forgotPassword;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.resetPassword.ResetPasswordActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordViewModel extends BaseObservable implements ForgotPasswordContract.ViewModel {

    private static final String TAG = ForgotPasswordViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;

    private ForgotPasswordContract.Presenter mPresenter;

    private String mIntroContent;

    private String mPhoneNumberError;
    private String mOptCodeError;
    private String mPhoneNumber;
    private String mOtpCode;

    private boolean mVisiblePhoneNumber;


    public ForgotPasswordViewModel(Context context, Navigator navigator, DialogManager dialogManager) {
        mContext = context;
        mNavigator = navigator;
        mDialogManager = dialogManager;

        mIntroContent = mContext.getString(R.string.intro_get_new_password);
        mVisiblePhoneNumber = true;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputPhoneNumberError(int errorMsg) {
        mPhoneNumberError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.phoneNumberError);
    }

    @Override
    public void onInputOtpCodeError(int errorMsg) {
        mOptCodeError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.otpCodeError);
    }

    @Override
    public void onFindPhoneNumberExistSuccess() {
        mPresenter.sendVerification(mPhoneNumber);
    }

    @Override
    public void onFindPhoneNumberExistError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));
    }

    @Override
    public void onVerificationSuccess(String firebaseToken) {
        Bundle extras = new Bundle();
        extras.putString(ForgotPasswordActivity.KEY_PHONE_NUMBER, mPhoneNumber);
        extras.putString(ForgotPasswordActivity.KEY_FIREBASE_TOKEN, firebaseToken);
        mNavigator.startActivity(ResetPasswordActivity.class, extras);
        mNavigator.finishActivity();
    }

    @Override
    public void onVerificationError(String message) {
        mNavigator.showToastCenterText(message);
    }

    @Override
    public void onVerificationError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));
    }

    @Override
    public void onWaitingTimeForResend(int duration) {
        String msg = String.format(mContext.getString(R.string.waiting_otp), String.valueOf(duration));
        mNavigator.showToastCenterText(msg);
    }

    @Override
    public void onSendVerificationSuccess() {
        // change intro content for add otp code
        mIntroContent = mContext.getString(R.string.intro_validate_opt);
        mVisiblePhoneNumber = false;
        notifyPropertyChanged(BR.visiblePhoneNumber);
        notifyPropertyChanged(BR.introContent);
    }

    @Override
    public void onSendVerificationClick() {
        Log.d(TAG, "phoneNumber: " + mPhoneNumber);
        mPresenter.checkUserExist(mPhoneNumber);
    }

    @Override
    public void onResendVerificationClick() {
        // resend verify number
        mPresenter.resendVerification(mPhoneNumber);
    }

    @Override
    public void onValidateClick() {
        // verify using code
        mPresenter.validateUser(mOtpCode);
    }

    @Override
    public void onBackPressed() {
        if (mVisiblePhoneNumber) {
            mNavigator.onBackPressed();
        } else onResetUI();
    }

    private void onResetUI() {
        mIntroContent = mContext.getString(R.string.intro_validate_phone);
        mVisiblePhoneNumber = true;
        notifyPropertyChanged(BR.visiblePhoneNumber);
        notifyPropertyChanged(BR.introContent);
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
    public String getPhoneNumberError() {
        return mPhoneNumberError;
    }

    @Bindable
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    @Bindable
    public String getOptCodeError() {
        return mOptCodeError;
    }

    @Bindable
    public String getOtpCode() {
        return mOtpCode;
    }

    public void setOtpCode(String mOptCode) {
        this.mOtpCode = mOptCode;
    }

    @Bindable
    public String getIntroContent() {
        return mIntroContent;
    }

    public void setIntroContent(String mIntroContent) {
        this.mIntroContent = mIntroContent;
    }

    @Bindable
    public boolean isVisiblePhoneNumber() {
        return mVisiblePhoneNumber;
    }

    public void setVisiblePhoneNumber(boolean visiblePhoneNumber) {
        mVisiblePhoneNumber = visiblePhoneNumber;
    }
}
