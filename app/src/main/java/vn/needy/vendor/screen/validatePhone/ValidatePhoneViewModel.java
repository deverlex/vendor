package vn.needy.vendor.screen.validatePhone;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.registerUser.RegisterUserActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class ValidatePhoneViewModel extends BaseObservable implements ValidatePhoneContract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private ValidatePhoneContract.Presenter mPresenter;

    private String mIntroContent;

    private String mPhoneNumberError;
    private String mOtpCodeError;
    private String mPhoneNumber;
    private String mOtpCode;

    private boolean mVisiblePhoneNumber;

    public ValidatePhoneViewModel(Context context, Navigator navigator, DialogManager dialogManager) {
        mContext = context;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mIntroContent = context.getString(R.string.intro_validate_phone);

        mVisiblePhoneNumber = true;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ValidatePhoneContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onVerificationSuccess(String accessToken) {
        Bundle extras = new Bundle();
        extras.putString(ValidatePhoneActivity.KEY_ACCESS_TOKEN, accessToken);
        mNavigator.startActivity(RegisterUserActivity.class, extras);
        mNavigator.finishActivity();
    }

    @Override
    public void onVerificationError(String errorMsg) {
        mNavigator.showToastButtom(errorMsg);
    }

    @Override
    public void onWaitingTimeForResend(String message) {
        mNavigator.showToastCenterText(message);
    }

    @Override
    public void onInputPhoneNumberError(int errorMsg) {
        mPhoneNumberError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.phoneNumberError);
    }

    @Override
    public void onInputOtpCodeError(int errorMsg) {
        mOtpCodeError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.otpCodeError);
    }

    @Override
    public void onSendVerificationClick() {
        // send verify number
        if (mPresenter.validateDataInput(mPhoneNumber)) {
            mPresenter.sendVerification(mPhoneNumber);
            // change intro content for add otp code
            mIntroContent = mContext.getString(R.string.intro_validate_opt);
            mVisiblePhoneNumber = false;
            notifyPropertyChanged(BR.visiblePhoneNumber);
            notifyPropertyChanged(BR.introContent);
        }
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
    public void onShowProgressBar() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
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

    @Bindable
    public String getIntroContent() {
        return mIntroContent;
    }

    public void setIntroContent(String mIntroContent) {
        this.mIntroContent = mIntroContent;
    }

    @Bindable
    public String getPhoneNumberError() {
        return mPhoneNumberError;
    }

    @Bindable
    public String getOtpCodeError() {
        return mOtpCodeError;
    }

    @Bindable
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    @Bindable
    public String getOptCode() {
        return mOtpCode;
    }

    public void setOptCode(String optCode) {
        this.mOtpCode = optCode;
    }

    @Bindable
    public boolean isVisiblePhoneNumber() {
        return mVisiblePhoneNumber;
    }

    public void setVisiblePhoneNumber(boolean visiblePhoneNumber) {
        mVisiblePhoneNumber = visiblePhoneNumber;
    }

}
