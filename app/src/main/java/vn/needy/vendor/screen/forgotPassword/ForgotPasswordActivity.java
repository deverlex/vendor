package vn.needy.vendor.screen.forgotPassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.databinding.ActivityForgotPasswordBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordActivity extends BaseActivity {

    private ForgotPasswordContract.ViewModel mViewModel;
    private ForgotPasswordContract.Presenter mPresenter;

    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_FIREBASE_TOKEN = "firebase_token";

    private RealmApi mRealmApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        Navigator navigator = new Navigator(this);
        DialogManager dialogManager = new DialogManager(this);

        mViewModel = new ForgotPasswordViewModel(this, getApplication(), navigator, dialogManager);

        mRealmApi = new RealmApi();

        mPresenter = new ForgotPasswordPresenter(this, mViewModel, mRealmApi);
        mViewModel.setPresenter(mPresenter);

        ActivityForgotPasswordBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        binding.setViewModel((ForgotPasswordViewModel) mViewModel);
    }

    @Override
    public void onDestroy() {
        mPresenter.onStop();
        super.onDestroy();
    }
}
