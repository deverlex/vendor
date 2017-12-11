package vn.needy.vendor.screen.registerUser;

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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.user.UserDataRemote;
import vn.needy.vendor.repository.remote.user.request.RegisterUserReq;
import vn.needy.vendor.repository.remote.user.response.TokenResponse;
import vn.needy.vendor.utils.Utils;

/**
 * Created by lion on 10/10/2017.
 */

public class RegisterUserPresenter implements RegisterUserContract.Presenter {

    private static final int TIME_DURATION = 15;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;

    private final RegisterUserContract.ViewModel mViewModel;
    private final Activity mActivity;

    private int mDuration;

    private final CompositeDisposable mCompositeDisposable;
    private final UserRepository mUserRepository;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            mViewModel.onSendVerificationSuccess();
            signInWithPhoneAuthCredential(credential);
            mDuration = -1;
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
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

    public RegisterUserPresenter(Activity activity, RegisterUserContract.ViewModel viewModel,
                                 VendorApi vendorApi, RealmApi realmApi, SharedPrefsApi prefsApi) {
        mViewModel = viewModel;
        mActivity = activity;
        mAuth = FirebaseAuth.getInstance();
        mDuration = 0;
        mUserRepository = new UserRepository(
                new UserDataRemote(vendorApi),
                new UserDataLocal(realmApi, prefsApi)
        );
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void sendVerification(String phoneNumber, String password) {
        if (!validateDataInput(phoneNumber, password)) {
            return;
        }
        phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
        sendVerificationPhone(phoneNumber);
        waitingForResend();
    }

    @Override
    public void resendVerification(String phoneNumber) {
        if (mDuration > 0) {
            mViewModel.onWaitingTimeForResend(mDuration);
        } else if (mResendToken != null) {
            phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
            resendVerificationCode(phoneNumber, mResendToken);
            waitingForResend();
        }
    }

    @Override
    public void validateVerification(String otpCode) {
        if (!TextUtils.isEmpty(otpCode)) {
            if (!TextUtils.isEmpty(mVerificationId)) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otpCode);
                signInWithPhoneAuthCredential(credential);
            } else {
                mViewModel.onVerificationError(R.string.validation_error);
            }
        } else {
            mViewModel.onInputOtpCodeError(R.string.otp_code_empty);
        }
    }

    @Override
    public void registerUser(RegisterUserReq request) {
        mUserRepository.registerUser(request)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                   mViewModel.onShowProgressBar();
                }
            }).doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onRegisterError(R.string.error_service);
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<TokenResponse>() {
                @Override
                public void accept(TokenResponse certification) throws Exception {
                    String token = certification.getToken();
                    if (!TextUtils.isEmpty(token)) {
                        mUserRepository.saveToken(certification.getToken());
                        mViewModel.onRegisterSuccess();
                    } else {
                        mViewModel.onRegisterError(certification.getMessage());
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onRegisterError(error.getMessage());
                }
            });
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

    private void sendVerificationPhone(String phoneNumber) {
        mViewModel.onShowProgressBar();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                TIME_DURATION,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                mActivity,          // Activity (for callback binding)
                mCallbacks);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        mViewModel.onShowProgressBar();
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
                if (task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    // Send token to your backend via HTTPS
                    mViewModel.onVerificationSuccess(user.getUid(), idToken);
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
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                if (mDuration == 0) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewModel.onVerificationError(R.string.validation_error);
                        }
                    });
                }
            }
        });
    }
}
