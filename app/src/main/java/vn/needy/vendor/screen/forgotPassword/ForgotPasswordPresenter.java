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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.UserLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
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

    private final CompositeDisposable mCompositeDisposable;

    private final Activity mActivity;

    private int mDuration;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            mViewModel.onHideProgressBar();
            mViewModel.onSendVerificationSuccess();
            signInWithPhoneAuthCredential(credential);
            mDuration = -1;
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

    public ForgotPasswordPresenter(ForgotPasswordContract.ViewModel viewModel, Activity activity) {
        mViewModel = viewModel;
        mActivity = activity;

        mAuth = FirebaseAuth.getInstance();
        mDuration = 0;

        mUserRepository = new UserRepository(new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(new RealmApi(), SharedPrefsImpl.getInstance()));
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void checkUserExist(String phoneNumber) {
        if (!validateDataInput(phoneNumber)) return;
        phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
        Disposable mDisposable = mUserRepository.findUserExist(phoneNumber)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mViewModel.onShowProgressBar();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        mViewModel.onFindPhoneNumberExistSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onHideProgressBar();
                        mViewModel.onFindPhoneNumberExistError(R.string.find_user_exist_error);
                    }
                });
        mCompositeDisposable.add(mDisposable);
    }

    @Override
    public void sendVerification(String phoneNumber) {
        phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
        sendVerificationPhone(phoneNumber);
        mViewModel.onShowProgressBar();
        waitingForResend();
    }

    @Override
    public void resendVerification(String phoneNumber) {
        if (mDuration > 0) {
            mViewModel.onWaitingTimeForResend(mDuration);
        } else if (mResendToken != null) {
            phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
            resendVerificationCode(phoneNumber, mResendToken);
            mViewModel.onShowProgressBar();
            waitingForResend();
        }
    }

    @Override
    public void validateUser(String otpCode) {
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
    public boolean validateDataInput(String phoneNumber) {
        boolean isValidate = true;
        if (!Utils.PhoneNumberUtils.isValidPhoneNumber(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.is_not_phone_number);
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.phone_number_empty);
        }
        return isValidate;
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
                            // get token access of user
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
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                if (mDuration == 0) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewModel.onHideProgressBar();
                            mViewModel.onVerificationError(R.string.validation_error);
                        }
                    });
                }
            }
        });
    }
}
