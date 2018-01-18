package vn.needy.vendor.screen.userProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.databinding.ActivityUserProfileBinding;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileActivity extends BaseActivity {

    private static final String TAG = UserProfileActivity.class.getName();

    private UserProfileContract.ViewModel mViewModel;

    private SharedPrefsApi mPrefsApi;
    private VendorApi mVendorApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityUserProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);

        mPrefsApi = SharedPrefsImpl.getInstance();
        mVendorApi = VendorServiceClient.getInstance();

        List<UserLocationContext> userLocations = new ArrayList<>();
        UserLocationAdapter locationAdapter = new UserLocationAdapter(this, userLocations);

        mViewModel = new UserProfileViewModel(this, locationAdapter);

        UserProfileContract.Presenter presenter =
                new UserProfilePresenter(mViewModel, mVendorApi, mPrefsApi);
        mViewModel.setPresenter(presenter);
        // will active some actions
        mViewModel.onStart();

        binding.setViewModel((UserProfileViewModel) mViewModel);
    }

    @Override
    public Fragment initFragment(@IdRes int target,
                        @NonNull Fragment fragment) {
        return super.initFragment(target, fragment);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
