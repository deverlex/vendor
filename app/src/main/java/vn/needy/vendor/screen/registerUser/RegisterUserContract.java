package vn.needy.vendor.screen.registerUser;

import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 03/10/2017.
 */

public class RegisterUserContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        /**Result for register*/
        void onRegisterError(String message);

        void onRegisterError(int errorMsg);

        void onRegisterSuccess();

        /**For result of verification*/
        void onVerificationSuccess(String firebaseUid, String firebaseToken);

        void onVerificationError(String message);

        void onVerificationError(int errorMsg);

        /**For action send verification to Firebase*/
        void onWaitingTimeForResend(int duration);

        void onSendVerificationSuccess();

        // send verify
        void onSendVerificationClick();

        // new otp
        void onResendVerificationClick();

        // done
        void onValidateClick();

        /**For data input error*/
        void onInputPhoneNumberError(int errorMsg);

        void onInputPasswordError(int errorMsg);

        void onInputOtpCodeError(int errorMsg);

        /**For view password*/
        void onTextChanged(CharSequence s, int start, int before, int count);

        void onViewPasswordClick();

        void onBackPressed();

        void onShowProgressBar();

        void onHideProgressBar();
    }

    interface Presenter extends BasePresenter {

        // Request to Firebase authentication
        void sendVerification(String phoneNumber, String password);

        // Resend request
        void resendVerification(String phoneNumber);

        // validate OptCode
        void validateVerification(String otpCode);

        void registerUser(RegisterUserReq request);

        boolean validateDataInput(String phoneNumber, String password);
    }
}
