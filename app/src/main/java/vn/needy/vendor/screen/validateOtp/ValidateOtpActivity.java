package vn.needy.vendor.screen.validateOtp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityValidateOtpBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by lion on 03/10/2017.
 */

public class ValidateOtpActivity extends BaseActivity {

    private ValidateOtpContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));
        mViewModel = new ValidateOtpViewModel();

        ActivityValidateOtpBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_validate_otp);
        binding.setViewModel((ValidateOtpViewModel) mViewModel);
    }
}
