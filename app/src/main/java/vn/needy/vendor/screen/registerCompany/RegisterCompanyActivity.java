package vn.needy.vendor.screen.registerCompany;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityRegisterCompanyBinding;
import vn.needy.vendor.screen.BaseActivity;

public class RegisterCompanyActivity extends BaseActivity {

    private RegisterCompanyContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        mViewModel = new RegisterCompanyViewModel();

        ActivityRegisterCompanyBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_company);
        binding.setViewModel((RegisterCompanyViewModel) mViewModel);
    }
}
