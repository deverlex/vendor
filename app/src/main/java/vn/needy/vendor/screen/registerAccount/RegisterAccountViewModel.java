package vn.needy.vendor.screen.registerAccount;

import android.content.Context;
import android.databinding.BaseObservable;

import vn.needy.vendor.screen.validateOtp.ValidateOtpActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 02/10/2017.
 */

public class RegisterAccountViewModel extends BaseObservable implements RegisterAccountContract.ViewModel {

    private final Navigator mNavigator;

    private String mPhoneNumberError;
    private String mPasswordError;
    private String mFirstNameError;
    private String mLastNameError;
    private String mAddressError;

    private String mPhoneNumber;
    private String mPassword;
    private String mFirstName;
    private String mLastName;
    private String mAddress;

    public RegisterAccountViewModel(Context context, Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(RegisterAccountContract.Presenter presenter) {

    }

    @Override
    public void onRegisterClick() {
        mNavigator.startActivity(ValidateOtpActivity.class);
    }

    @Override
    public void onBackLoginClick() {

    }


}
