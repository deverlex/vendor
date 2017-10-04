package vn.needy.vendor.screen.validateOtp;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 03/10/2017.
 */

public class ValidateOtpContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onDone();

        void onResend();
    }

    interface Presenter extends BasePresenter {
        void validateOtp(String otp);

        void resend();
    }
}
