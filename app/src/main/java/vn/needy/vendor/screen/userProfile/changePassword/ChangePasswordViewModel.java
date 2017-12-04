package vn.needy.vendor.screen.userProfile.changePassword;

import android.app.Activity;
import android.databinding.BaseObservable;

/**
 * Created by truongpq on 04/12/2017.
 */

public class ChangePasswordViewModel extends BaseObservable implements ChangePasswordConstract.ViewModel{
    private Activity mActivity;

    public ChangePasswordViewModel(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ChangePasswordConstract.Presenter presenter) {

    }

    @Override
    public void onBackPressed() {
        mActivity.onBackPressed();
    }
}
