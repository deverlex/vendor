package vn.needy.vendor.screen.registerAccount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityRegisterAccountBinding;
import vn.needy.vendor.screen.BaseActivity;

public class RegisterAccountActivity extends BaseActivity {

    private RegisterContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

        mViewModel = new RegisterViewModel();

        ActivityRegisterAccountBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_account);
        binding.setViewModel((RegisterViewModel) mViewModel);
    }
}
