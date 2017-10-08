package vn.needy.vendor.screen.registerAccount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityRegisterAccountBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

public class RegisterAccountActivity extends BaseActivity {

    private RegisterAccountContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        Navigator navigator = new Navigator(this);

        mViewModel = new RegisterAccountViewModel(this, navigator);

        ActivityRegisterAccountBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_account);
        binding.setViewModel((RegisterAccountViewModel) mViewModel);
    }
}
