package vn.needy.vendor.screen.resetPassword;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 04/10/2017.
 */

public class ResetPasswordContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onInputPasswordError(int errorMsg);

        void onClickResetPassword();

        void onResetPasswordError(String message);

        void onResetPasswordSuccess();

        void onShowProgressBar();

        void onHideProgressBar();

        void onRedirectToMain();

        void onRedirectToRegisterCompany();
    }

    interface Presenter extends BasePresenter {

        void resetPassword(String phoneNumber, String firebaseToken, String password);

        boolean validateDataInput(String password);

        void findCompanyInherent();
    }
}
