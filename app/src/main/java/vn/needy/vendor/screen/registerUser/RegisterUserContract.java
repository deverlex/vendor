package vn.needy.vendor.screen.registerUser;

import android.widget.RadioGroup;

import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 02/10/2017.
 */

interface RegisterUserContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onInputFirstNameError(int errorMsg);

        void onInputLastNameError(int errorMsg);

        void onInputPasswordError(int errorMsg);

        void onRegisterError(String message);

        void onRegisterError(int errorMsg);

        void onRegisterSuccess();

        void onRegisterClick();

        void onCheckedChanged(RadioGroup radioGroup, int id);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void onViewPasswordClick();

        void onShowProgressBar();

        void onHideProgressBar();

        void setLat(float lat);

        void setLng(float lng);

        void onRedirectToMain();

        void onRedirectToRegisterCompany();

        void onBackPressed();
    }

    interface Presenter extends BasePresenter {

        void registerUser(RegisterUserRequest registerUserRequest);

        boolean validateDataInput(String passWord, String firstName, String lastName);

        void findCompanyInherent();
    }
}
