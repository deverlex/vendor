package vn.needy.vendor.screen.registerUser;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.widget.RadioGroup;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.R;
import vn.needy.vendor.data.model.GenderType;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.screen.registerCompany.RegisterCompanyActivity;
import vn.needy.vendor.screen.validatePhone.ValidatePhoneActivity;
import vn.needy.vendor.utils.Utils;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 02/10/2017.
 */

public class RegisterUserViewModel extends BaseObservable implements RegisterUserContract.ViewModel {

    private final static String TAG = RegisterUserViewModel.class.getName();

    private Context mContext;
    private final Application mApplication;
    private final Navigator mNavigator;
    private RegisterUserContract.Presenter mPresenter;

    private String mPasswordError;
    private String mFirstNameError;
    private String mLastNameError;

    private String mPhoneNumber;
    private String mPassword;
    private String mFirstName;
    private String mLastName;
    private String mAddress;
    private String mGender;
    private float mLat;
    private float mLng;

    private int mDrawableShowPassword;
    private boolean mVisibleShowPassword;
    private TransformationMethod mTransformationMethod;

    public RegisterUserViewModel(Application application, Context context, Navigator navigator) {
        mApplication = application;
        mContext = context;
        mNavigator = navigator;

        mDrawableShowPassword = R.drawable.ic_eye_off;
        mTransformationMethod = PasswordTransformationMethod.getInstance();
        mGender = GenderType.FEMALE;

        Bundle extras = ((Activity) mContext).getIntent().getExtras();
        mPhoneNumber = extras.getString(ValidatePhoneActivity.KEY_PHONE_NUMBER);

        mFirstName = "Nguyen";
        mLastName = "Do";
        mPassword = "12345678";
        mAddress = "Le Quy Don";
        mLat = 12.86f;
        mLng = 13.94f;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(RegisterUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputFirstNameError(int errorMsg) {
        mFirstNameError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.firstNameError);
    }

    @Override
    public void onInputLastNameError(int errorMsg) {
        mLastNameError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.lastNameError);
    }

    @Override
    public void onInputPasswordError(int errorMsg) {
        mPasswordError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.passwordError);
    }

    @Override
    public void onRegisterError(String message) {
        mNavigator.showToastCenterText(message);
    }

    @Override
    public void onRegisterError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));
    }

    @Override
    public void onRegisterSuccess() {
        Log.w(TAG, "onRegisterSuccess");
        VendorServiceClient.initialize(mApplication);
        mPresenter.findCompanyInherent();
    }

    @Override
    public void onRegisterClick() {
        Bundle extras = ((Activity) mContext).getIntent().getExtras();
        String uid = extras.getString(ValidatePhoneActivity.KEY_FIREBASE_UID);
        String accessToken = extras.getString(ValidatePhoneActivity.KEY_FIREBASE_TOKEN);

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirebaseUid(uid);
        registerUserRequest.setFirebaseToken(accessToken);
        registerUserRequest.setPhoneNumber(Utils.PhoneNumberUtils.formatPhoneNumber(getPhoneNumber()));
        registerUserRequest.setPassword(mPassword);
        registerUserRequest.setFirstName(mFirstName);
        registerUserRequest.setLastName(mLastName);
        registerUserRequest.setGender(mGender);
        registerUserRequest.setAddress(mAddress);
        registerUserRequest.setLat(mLat);
        registerUserRequest.setLng(mLng);

        mPresenter.registerUser(registerUserRequest);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        if (id == R.id.rbMale) mGender = GenderType.MALE;
        else if (id == R.id.rbFemale) mGender = GenderType.FEMALE;
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
    public void onShowProgressBar() {

    }

    @Override
    public void onHideProgressBar() {

    }

    @Override
    public void setLat(float lat) {
        mLat = lat;
    }

    @Override
    public void setLng(float lng) {
        mLng = lng;
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
    public boolean isVisibleShowPassword() {
        return mVisibleShowPassword;
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

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Bindable
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    @Bindable
    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    @Bindable
    public String getPasswordError() {
        return mPasswordError;
    }

    @Bindable
    public String getFirstNameError() {
        return mFirstNameError;
    }

    @Bindable
    public String getLastNameError() {
        return mLastNameError;
    }
}
