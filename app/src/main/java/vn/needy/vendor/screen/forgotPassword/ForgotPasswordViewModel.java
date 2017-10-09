package vn.needy.vendor.screen.forgotPassword;

import android.content.Context;
import android.databinding.BaseObservable;

import vn.needy.vendor.screen.resetPassword.ResetPasswordActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordViewModel extends BaseObservable implements ForgotPasswordContract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;

    public ForgotPasswordViewModel(Context context, Navigator navigator) {
        mContext = context;
        mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {

    }

    @Override
    public void onRequireOtpClick() {
        mNavigator.startActivity(ResetPasswordActivity.class);
    }

    @Override
    public void onRegisterClick() {

    }
}
