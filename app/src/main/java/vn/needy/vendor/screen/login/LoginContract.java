package vn.needy.vendor.screen.login;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

public interface LoginContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onLoginSuccess();

        void onLoginError(int errorMsg);

        void onLoginError(String message);

        void onInputPhoneNumberError(int errorMsg);

        void onInputPasswordError(int errorMsg);

        void onLoginClick();

        void onSignUpClick();

        void onForgotPasswordClick();

        void onShowProgressBar();

        void onHideProgressBar();

        void onViewPasswordClick();

        void onPasswordTextChanged(CharSequence s, int start, int before, int count);

        void onRedirectToMain();

        void onRedirectToRegisterCompany();
    }

    interface Presenter extends BasePresenter {
        void login(String phoneNumber, String passWord);

        boolean validateDataInput(String phoneNumber, String passWord);

        void findCompanyReference();
    }
}
