package vn.needy.vendor.screen.resetPassword;

import android.content.Context;
import android.databinding.BaseObservable;

import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 04/10/2017.
 */

public class ResetPasswordViewModel extends BaseObservable implements ResetPasswordContract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;

    private ResetPasswordContract.Presenter mPresenter;

    public ResetPasswordViewModel(Context context, Navigator navigator) {
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
    public void setPresenter(ResetPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputPasswordError(int errorMsg) {

    }

    @Override
    public void onClickResetPassword() {

    }

    @Override
    public void onShowProgressBar() {

    }

    @Override
    public void onHideProgressBar() {

    }
}
