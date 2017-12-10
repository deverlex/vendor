package vn.needy.vendor.screen.forgotPassword;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.R;
import vn.needy.vendor.repository.remote.user.request.ResetPasswordRequest;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordViewModel extends BaseObservable implements ForgotPasswordContract.ViewModel {

    private static final String TAG = ForgotPasswordViewModel.class.getName();

    private final Context mContext;
    private final Application mApplication;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;

    private ForgotPasswordContract.Presenter mPresenter;

    private String mIntroContent;

    private String mPhoneNumberError;
    private String mPasswordError;
    private String mOtpCodeError;

    private String mPhoneNumber;
    private String mPassword;
    private String mOtpCode;

    private String mFirebaseToken;

    private int mDrawableShowPassword;
    private boolean mVisibleShowPassword;
    private TransformationMethod mTransformationMethod;

    private boolean mVisibleOtpCode;
    private boolean mVisiblePassword;

    public ForgotPasswordViewModel(Context context, Application application, Navigator navigator, DialogManager dialogManager) {
        mContext = context;
        mApplication = application;
        mNavigator = navigator;
        mDialogManager = dialogManager;

        mIntroContent = mContext.getString(R.string.intro_get_new_password);

        mVisibleOtpCode = false;
        mVisiblePassword = false;

        mDrawableShowPassword = R.drawable.ic_eye_off;
        mTransformationMethod = PasswordTransformationMethod.getInstance();
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
        mOtpCodeError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.otpCodeError);
    }

    @Override
    public void onInputPasswordError(int errorMsg) {
        mPasswordError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.passwordError);
    }

    @Override
    public void onFindPhoneNumberExistSuccess() {
        mPresenter.sendVerifyPhoneNumber(mPhoneNumber);
    }

    @Override
    public void onFindPhoneNumberExistError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));
    }

    @Override
    public void onVerificationSuccess(String firebaseToken) {
        mVisiblePassword = true;
        mVisibleOtpCode = false;
        notifyPropertyChanged(BR.visiblePassword);
        notifyPropertyChanged(BR.visibleOtpCode);

        mFirebaseToken = firebaseToken;
        notifyPropertyChanged(BR.focusPhoneNumber);
    }

    @Override
    public void onVerificationError(String message) {
        mNavigator.showToastCenterText(message);

        mVisibleOtpCode = true;
        mIntroContent = mContext.getString(R.string.intro_reset_password);
        notifyPropertyChanged(BR.visibleOtpCode);
        notifyPropertyChanged(BR.introContent);
        notifyPropertyChanged(BR.focusPhoneNumber);
    }

    @Override
    public void onVerificationError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));

        mVisibleOtpCode = true;
        mIntroContent = mContext.getString(R.string.intro_reset_password);
        notifyPropertyChanged(BR.visibleOtpCode);
        notifyPropertyChanged(BR.introContent);
        notifyPropertyChanged(BR.focusPhoneNumber);
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
        mVisibleOtpCode = true;
        notifyPropertyChanged(BR.visibleOtpCode);
        notifyPropertyChanged(BR.introContent);
        notifyPropertyChanged(BR.focusPhoneNumber);
    }

    @Override
    public void onVerifyPhoneNumberClick() {
        Log.d(TAG, "phoneNumber: " + mPhoneNumber);
        mPresenter.checkUserExist(mPhoneNumber);
    }

    @Override
    public void onResendVerifyPhoneNumberClick() {
        // resend verify number
        mPresenter.resendVerifyPhoneNumber(mPhoneNumber);
    }

    @Override
    public void onValidateClick() {
        // verify using code
        mPresenter.validateOtpCode(mOtpCode);
    }

    @Override
    public void onResetPasswordClick() {

        Log.d(TAG, "password? " + mPassword);
        Log.d(TAG, "TOKEN? " + mFirebaseToken);

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setFirebaseToken(mFirebaseToken);
        request.setPassword(mPassword);
        mPresenter.resetPassword(mPhoneNumber, request);

        Log.d(TAG, "onResetPasswordClick");
    }

    @Override
    public void onBackPressed() {
        if (mVisibleOtpCode) {
            reset();
        } else mNavigator.onBackPressed();
    }

    private void reset() {
        mIntroContent = mContext.getString(R.string.intro_validate_phone);
        mVisibleOtpCode = false;
        notifyPropertyChanged(BR.visibleOptCode);
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

    @Override
    @Bindable
    public boolean isFocusPhoneNumber() {
        return !mVisibleOtpCode && !mVisiblePassword;
    }

    @Override
    public void onResetPasswordError(String message) {

    }

    @Override
    public void onResetPasswordSuccess() {
        mDialogManager.dismissProgressDialog();
        VendorServiceClient.initialize(mApplication);
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            mVisibleShowPassword = true;
        } else mVisibleShowPassword = false;
        notifyPropertyChanged(BR.visibleShowPassword);
    }

    @Override
    public void onViewPasswordClick() {
        notifyPropertyChanged(BR.visibleShowPassword);
        if (mDrawableShowPassword == R.drawable.ic_eye) {
            mDrawableShowPassword = R.drawable.ic_eye_off;
            mTransformationMethod = PasswordTransformationMethod.getInstance();
        } else {
            mDrawableShowPassword = R.drawable.ic_eye;
            mTransformationMethod = HideReturnsTransformationMethod.getInstance();
        }
        notifyPropertyChanged(BR.drawableShowPassword);
        notifyPropertyChanged(BR.transformationMethod);
    }

    @Bindable
    public String getPhoneNumberError() {
        return mPhoneNumberError;
    }

    @Bindable
    public String getPasswordError() {
        return mPasswordError;
    }

    @Bindable
    public String getOtpCodeError() {
        return mOtpCodeError;
    }

    @Bindable
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
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
    public boolean isVisibleOtpCode() {
        return mVisibleOtpCode;
    }

    public void setVisibleOtpCode(boolean visibleOptCode) {
        mVisibleOtpCode = visibleOptCode;
    }

    @Bindable
    public boolean isVisiblePassword() {
        return mVisiblePassword;
    }

    public void setVisiblePassword(boolean visiblePassword) {
        this.mVisiblePassword = visiblePassword;
    }

    @Bindable
    public int getDrawableShowPassword() {
        return mDrawableShowPassword;
    }

    @Bindable
    public TransformationMethod getTransformationMethod() {
        return mTransformationMethod;
    }

    @Bindable
    public boolean isVisibleShowPassword() {
        return mVisibleShowPassword;
    }
}
