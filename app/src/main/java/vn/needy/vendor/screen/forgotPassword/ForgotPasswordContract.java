package vn.needy.vendor.screen.forgotPassword;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onRequireOtpClick();

        void onRegisterClick();
    }

    interface Presenter extends BasePresenter {

        void resendRequireOtp();

        void validateDataInput(String phoneNumber);

    }
}
