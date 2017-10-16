package vn.needy.vendor.screen.validatePhone;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 03/10/2017.
 */

public class ValidatePhoneContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onVerificationSuccess(String firebaseUid, String firebaseToken);

        void onVerificationError(String message);

        void onVerificationError(int errorMsg);

        void onWaitingTimeForResend(int duration);

        void onInputPhoneNumberError(int errorMsg);

        void onInputOtpCodeError(int errorMsg);

        void onSendVerificationSuccess();

        // send verify
        void onSendVerificationClick();

        // new otp
        void onResendVerificationClick();

        // done
        void onValidateClick();

        void onShowProgressBar();

        void onHideProgressBar();

        void onBackPressed();
    }

    interface Presenter extends BasePresenter {

        void sendVerification(String phoneNumber);

        void resendVerification(String phoneNumber);

        void validateUser(String otpCode);

        boolean validateDataInput(String phoneNumber);
    }
}
