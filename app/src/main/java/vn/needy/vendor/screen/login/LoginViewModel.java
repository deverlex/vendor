package vn.needy.vendor.screen.login;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.R;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.forgotPassword.ForgotPasswordActivity;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.registerCompany.RegisterCompanyActivity;
import vn.needy.vendor.screen.registerUser.RegisterUserActivity;
import vn.needy.vendor.utils.Utils;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

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
    private int mDrawableShowPassword;
    private boolean mVisibleShowPassword;
    private TransformationMethod mTransformationMethod;

    LoginViewModel(Context context, Application application,
                   Navigator navigator, DialogManager dialogManager) {
        mContext = context;
        mApplication = application;
        mNavigator = navigator;
        mDialogManager = dialogManager;

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
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoginSuccess() {
        VendorServiceClient.initialize(mApplication);
        mPresenter.findCompany();
    }

    @Override
    public void onLoginError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));
    }

    @Override
    public void onLoginError(String message) {
        mNavigator.showToastCenterText(message);
    }

    @Override
    public void onInputPhoneNumberError(int errorMsg) {
        mPhoneNumberError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.phoneNumberError);
    }

    @Override
    public void onInputPasswordError(int errorMsg) {
        mPasswordError = mContext.getString(errorMsg);
        mVisibleShowPassword = false;
        notifyPropertyChanged(BR.passwordError);
        notifyPropertyChanged(BR.visibleShowPassword);
    }

    @Override
    public void onLoginClick() {
        mPhoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(mPhoneNumber);
        mPresenter.login(mPhoneNumber, mPassword);
    }

    @Override
    public void onSignUpClick() {
        mNavigator.startActivity(RegisterUserActivity.class);
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

    @Override
    public void onViewPasswordClick() {
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

    @Override
    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            mVisibleShowPassword = true;
        } else mVisibleShowPassword = false;
        notifyPropertyChanged(BR.visibleShowPassword);
    }

    @Override
    public void onToMainPage() {
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }

    @Override
    public void onToRegisterCompany() {
        mNavigator.startActivity(RegisterCompanyActivity.class);
        mNavigator.finishActivity();
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

    @Bindable
    public int getDrawableShowPassword() {
        return mDrawableShowPassword;
    }

    @Bindable
    public boolean isVisibleShowPassword() {
        return mVisibleShowPassword;
    }

    @Bindable
    public TransformationMethod getTransformationMethod() {
        return mTransformationMethod;
    }

}
