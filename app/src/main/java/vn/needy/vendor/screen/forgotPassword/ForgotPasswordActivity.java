package vn.needy.vendor.screen.forgotPassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityForgotPasswordBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/10/2017.
 */

public class ForgotPasswordActivity extends BaseActivity {

    private ForgotPasswordContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        Navigator navigator = new Navigator(this);

        mViewModel = new ForgotPasswordViewModel(this, navigator);

        ActivityForgotPasswordBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        binding.setViewModel((ForgotPasswordViewModel) mViewModel);
    }
}
