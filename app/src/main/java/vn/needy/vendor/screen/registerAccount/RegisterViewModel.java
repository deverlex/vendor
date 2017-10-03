package vn.needy.vendor.screen.registerAccount;

import android.databinding.BaseObservable;

/**
 * Created by lion on 02/10/2017.
 */

public class RegisterViewModel extends BaseObservable implements RegisterContract.ViewModel {

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

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {

    }

    @Override
    public void onRegisterClick() {

    }

    @Override
    public void onBackLogin() {

    }


}
