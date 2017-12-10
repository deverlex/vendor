package vn.needy.vendor.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import vn.needy.vendor.R;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
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

    private SharedPrefsApi mPrefsApi;
    private VendorApi mVendorApi;
    private RealmApi mRealmApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));

        //Clear data
        mPrefsApi = SharedPrefsImpl.getInstance();
        mPrefsApi.clear();
        mVendorApi = VendorServiceClient.initialize(getApplication());
        mRealmApi = new RealmApi();

        mNavigator = new Navigator(this);

        DialogManager dialogManager = new DialogManager(this);
        mViewModel = new LoginViewModel(this, getApplication(), mNavigator, dialogManager);

        LoginContract.Presenter presenter = new LoginPresenter(mViewModel, mNavigator,
                mVendorApi, mRealmApi, mPrefsApi);

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
