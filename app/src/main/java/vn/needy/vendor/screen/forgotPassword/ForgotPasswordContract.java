package vn.needy.vendor.screen.forgotPassword;

import vn.needy.vendor.data.source.remote.api.request.ResetPasswordRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;
import vn.needy.vendor.screen.resetPassword.ResetPasswordContract;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordContract {

    interface ViewModel extends BaseViewModel<ForgotPasswordContract.Presenter> {

        /**For reset password*/
        void onResetPasswordError(String message);

        void onResetPasswordSuccess();

        /**For result of verification*/
        void onVerificationSuccess(String firebaseToken);

        void onVerificationError(String message);

        void onVerificationError(int errorMsg);

        // when click on verify phone number
        void onSendVerificationSuccess();

        /**For action send verification to Firebase*/
        void onWaitingTimeForResend(int duration);

        void onFindPhoneNumberExistSuccess();

        void onFindPhoneNumberExistError(int errorMsg);

        // send verify
        void onVerifyPhoneNumberClick();

        // new otp
        void onResendVerifyPhoneNumberClick();

        // done
        void onValidateClick();

        void onResetPasswordClick();

        /**For data input error*/
        void onInputPasswordError(int errorMsg);

        void onInputPhoneNumberError(int errorMsg);

        void onInputOtpCodeError(int errorMsg);

        /**For view password*/

        void onTextChanged(CharSequence s, int start, int before, int count);

        void onViewPasswordClick();

        void onBackPressed();

        void onShowProgressBar();

        void onHideProgressBar();

        boolean isFocusPhoneNumber();
    }

    interface Presenter extends BasePresenter {

        void checkUserExist(String phoneNumber);

        void sendVerifyPhoneNumber(String phoneNumber);

        void resendVerifyPhoneNumber(String phoneNumber);

        void validateOtpCode(String otpCode);

        void resetPassword(String phoneNumber, ResetPasswordRequest request);

        boolean validateDataInput(String phoneNumber, String password);
    }
}
