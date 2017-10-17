package vn.needy.vendor.screen.forgotPassword;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;
import vn.needy.vendor.screen.resetPassword.ResetPasswordContract;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordContract {

    interface ViewModel extends BaseViewModel<ForgotPasswordContract.Presenter> {

        void onInputPhoneNumberError(int errorMsg);

        void onInputOtpCodeError(int errorMsg);

        void onFindPhoneNumberExistSuccess();

        void onFindPhoneNumberExistError(int errorMsg);

        void onVerificationSuccess(String firebaseToken);

        void onVerificationError(String message);

        void onVerificationError(int errorMsg);

        void onWaitingTimeForResend(int duration);

        void onSendVerificationSuccess();

        // send verify
        void onSendVerificationClick();

        // new otp
        void onResendVerificationClick();

        // done
        void onValidateClick();

        void onBackPressed();

        void onShowProgressBar();

        void onHideProgressBar();
    }

    interface Presenter extends BasePresenter {

        void checkUserExist(String phoneNumber);

        void sendVerification(String phoneNumber);

        void resendVerification(String phoneNumber);

        void validateUser(String otpCode);

        boolean validateDataInput(String phoneNumber);
    }
}
