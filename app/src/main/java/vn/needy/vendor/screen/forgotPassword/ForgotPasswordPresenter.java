package vn.needy.vendor.screen.forgotPassword;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.port.message.BaseCode;
import vn.needy.vendor.port.message.BaseStatus;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.user.UserDataRemote;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.repository.remote.user.request.ResetAccountRequest;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.utils.Utils;

/**
 * Created by lion on 17/10/2017.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    private static final int TIME_DURATION = 60;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;

    private ForgotPasswordContract.ViewModel mViewModel;
    private UserRepository mUserRepository;

    private final Activity mActivity;

    private int mDuration;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            mViewModel.onSendVerificationSuccess(credential.getSmsCode());
            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            mViewModel.onHideProgressBar();
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                mViewModel.onInputPhoneNumberError(R.string.is_not_phone_number);
            } else if (e instanceof FirebaseTooManyRequestsException) {
                mViewModel.onVerificationError(R.string.resend_over_limit);
            } else {
                mViewModel.onVerificationError(R.string.validation_error);
            }
        }

        @Override
        public void onCodeSent(String verificationId,
                               PhoneAuthProvider.ForceResendingToken token) {
            mVerificationId = verificationId;
            mResendToken = token;
        }
    };

    public ForgotPasswordPresenter(Activity activity, ForgotPasswordContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mActivity = activity;

        mAuth = FirebaseAuth.getInstance();
        mDuration = 0;

        mUserRepository = new UserRepository(
                new UserDataRemote(VendorServiceClient.getInstance()),
                new UserDataLocal(SharedPrefsImpl.getInstance()));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void checkUserExist(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) return;
        phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
        mUserRepository.findUserExist(phoneNumber)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    mViewModel.onShowProgressBar();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseWrapper>() {
                @Override
                public void accept(ResponseWrapper baseResponse) throws Exception {
                    mViewModel.onHideProgressBar();
                    if (baseResponse.getCode() == BaseCode.OK) {
                        mViewModel.onFindPhoneNumberExistSuccess();
                    } else {
                        mViewModel.onFindPhoneNumberExistError(R.string.find_user_exist_error);
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onHideProgressBar();
                    mViewModel.onFindPhoneNumberExistError(R.string.find_user_exist_error);
                }
            });
    }

    @Override
    public void sendVerifyPhoneNumber(String phoneNumber) {
        phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
        sendVerificationPhone(phoneNumber);
        waitingForResend();
        mViewModel.onShowOtpCodeView();
    }

    @Override
    public void resendVerifyPhoneNumber(String phoneNumber) {
        if (mDuration > 0) {
            mViewModel.onWaitingTimeForResend(mDuration);
        } else if (mResendToken != null) {
            phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
            resendVerificationCode(phoneNumber, mResendToken);
            waitingForResend();
        }
    }

    @Override
    public void validateOtpCode(String otpCode) {
        if (!TextUtils.isEmpty(otpCode)) {
            if (!TextUtils.isEmpty(mVerificationId)) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otpCode);
                signInWithPhoneAuthCredential(credential);
                mViewModel.onShowProgressBar();
            } else {
                mViewModel.onVerificationError(R.string.validation_error);
            }
        } else {
            mViewModel.onInputOtpCodeError(R.string.otp_code_empty);
        }
    }

    @Override
    public boolean validateDataInput(String phoneNumber, String password) {
        boolean isValidate = true;
        if (!Utils.PhoneNumberUtils.isValidPhoneNumber(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.is_not_phone_number);
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.phone_number_empty);
        }
        if (TextUtils.isEmpty(password)) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_empty);
        }
        if (!TextUtils.isEmpty(password) && password.length() > 32) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_over_limit);
        }
        if (!TextUtils.isEmpty(password) && password.length() < 8) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_miss_length);
        }
        return isValidate;
    }

    @Override
    public void checkCompany() {
        updateRepositoryAfterResetApi();

        // check company
        mUserRepository.checkOwnCompanyExist()
            .subscribeOn(Schedulers.io())
            .doAfterTerminate(new Action() {
                @Override
                public void run() throws Exception {
                    mViewModel.onHideProgressBar();
                }
            })
            .doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onResetPasswordError(error.getMessage());
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseWrapper>() {
                @Override
                public void accept(ResponseWrapper responseWrapper) throws Exception {
                    if (responseWrapper.getStatus().equals(BaseStatus.OK)) {
                        mViewModel.onToMainPage();
                    } else {
                        mViewModel.onToRegisterCompany();
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onResetPasswordError(error.getMessage());
                }
            });
    }

    @Override
    public void resetPassword(String phoneNumber, ResetAccountRequest request) {
        if (!validateDataInput(phoneNumber, request.getPassword())) return;

        phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
        mUserRepository.resetPassword(phoneNumber, request)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    mViewModel.onShowProgressBar();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseWrapper<TokenResponse>>() {
                @Override
                public void accept(ResponseWrapper<TokenResponse> certification) throws Exception {
                    TokenResponse data = certification.getData();
                    if (data != null && !TextUtils.isEmpty(data.getTokenAccess())) {
                        mUserRepository.saveAccessTokenSync(data.getTokenAccess());
                        mUserRepository.saveRefreshTokenSync(data.getRefreshToken());
                        mUserRepository.saveExpiresIn(data.getExpiresIn());

                        mViewModel.onResetPassSuccess();
                    } else {
                        mViewModel.onResetPasswordError(certification.getMessage());
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onHideProgressBar();
                }
            });
    }

    private void sendVerificationPhone(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                TIME_DURATION,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                mActivity,          // Activity (for callback binding)
                mCallbacks);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                TIME_DURATION,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                mActivity,          // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // getAsync token access of user
                        getUserTokenId(task.getResult().getUser());
                    } else {
                        mViewModel.onHideProgressBar();
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            mViewModel.onInputOtpCodeError(R.string.otp_code_invalid);
                        }
                    }
                }
            });
    }

    private void getUserTokenId(final FirebaseUser user) {
        user.getIdToken(true).addOnCompleteListener(mActivity, new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                mViewModel.onHideProgressBar();
                if (task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    // Send token to your backend via HTTPS
                    mViewModel.onVerificationSuccess(idToken);
                } else {
                    mViewModel.onVerificationError(R.string.validation_error);
                }
            }
        });
    }

    private void waitingForResend() {
        mDuration = TIME_DURATION;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                while (mDuration > 0) {
                    mDuration -= 1;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewModel.countDownTimeOtpCode(mDuration);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    private void updateRepositoryAfterResetApi() {
        // update API
        mUserRepository = new UserRepository(
                new UserDataRemote(VendorServiceClient.getInstance()),
                new UserDataLocal(SharedPrefsImpl.getInstance())
        );
    }
}
