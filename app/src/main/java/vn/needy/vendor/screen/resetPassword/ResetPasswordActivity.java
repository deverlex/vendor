package vn.needy.vendor.screen.resetPassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityResetPasswordBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 04/10/2017.
 */

public class ResetPasswordActivity extends BaseActivity {

    private ResetPasswordContract.ViewModel mViewModel;


    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));


        Navigator navigator = new Navigator(this);
        mViewModel = new ResetPasswordViewModel(this, navigator);



        ActivityResetPasswordBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        binding.setViewModel((ResetPasswordViewModel) mViewModel);
    }
}
