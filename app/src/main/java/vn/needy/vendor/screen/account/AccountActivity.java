package vn.needy.vendor.screen.account;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.databinding.ActivityAccountBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 05/12/2017.
 */

public class AccountActivity extends BaseActivity {

    private AccountContract.ViewModel mAccountViewModel;
    private AccountContract.Presenter mPresenter;

    private SharedPrefsApi mPrefsApi;
    private Navigator mNavigator;
    private Realm mRealm;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

        mNavigator = new Navigator(this);
        DialogManager dialogManager = new DialogManager(this);
        mPrefsApi = SharedPrefsImpl.getInstance();

        mPresenter = new AccountPresenter(this);
        mRealm = Realm.getDefaultInstance();
        mAccountViewModel = new AccountViewModel(mNavigator,this,dialogManager,getApplication() , mPrefsApi , mRealm);

        mAccountViewModel.setPresenter(mPresenter);
        mAccountViewModel.onStart();

        ActivityAccountBinding accountBinding = DataBindingUtil.setContentView(this , R.layout.activity_account);
        accountBinding.setViewModel((AccountViewModel) mAccountViewModel);
    }

    @Override
    public Fragment initFragment(@IdRes int target,@NonNull Fragment fragment) {
        return super.initFragment(target, fragment);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else {
            super.onBackPressed();
        }
    }

}
