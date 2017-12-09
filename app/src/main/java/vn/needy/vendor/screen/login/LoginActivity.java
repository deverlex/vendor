package vn.needy.vendor.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.service.VendorServiceClient;
import vn.needy.vendor.databinding.ActivityLoginBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 02/10/2017.
 */
public class LoginActivity extends BaseActivity {

    private LoginContract.ViewModel mViewModel;
    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        //Clear data
        SharedPrefsImpl.getInstance().clear();
        VendorServiceClient.initialize(getApplication());
        mNavigator = new Navigator(this);

        DialogManager dialogManager = new DialogManager(this);
        mViewModel = new LoginViewModel(this, getApplication(), mNavigator, dialogManager);

        LoginContract.Presenter presenter = new LoginPresenter(mViewModel, mNavigator,
                SharedPrefsImpl.getInstance());

        mViewModel.setPresenter(presenter);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel((LoginViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
