package vn.needy.vendor.screen.login;

import android.support.annotation.DrawableRes;

import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

public interface LoginContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onLoginSuccess();

        void onInputPhoneNumberError(int errorMsg);

        void onInputPasswordError(int errorMsg);

        void onLoginError(BaseException exception);

        void onLoginClick();

        void onSignUpClick();

        void onForgotPasswordClick();

        void onShowProgressBar();

        void onHideProgressBar();

        void onViewPasswordClick();

        void onPasswordTextChanged(CharSequence s, int start, int before, int count);
    }

    interface Presenter extends BasePresenter {
        void login(String phoneNumber, String passWord);

        boolean validateDataInput(String phoneNumber, String passWord);
    }
}
