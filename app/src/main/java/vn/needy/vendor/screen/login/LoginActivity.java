package vn.needy.vendor.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityLoginBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 02/10/2017.
 */

public class LoginActivity extends BaseActivity {

    private LoginContract.ViewModel mViewModel;


    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        Navigator navigator = new Navigator(this);
        mViewModel = new LoginViewModel(this, navigator);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel((LoginViewModel) mViewModel);
    }
}
