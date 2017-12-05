package vn.needy.vendor.screen.userProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ScrollView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.needy.vendor.R;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.databinding.ActivityUserProfileBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.widget.WorkaroundMapFragment;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileActivity extends BaseActivity {

    private static final String CLASS = UserProfileActivity.class.getName();

    private UserProfileContract.ViewModel mViewModel;
    private ScrollView mScrollView;

    private RealmApi mRealmApi;
    private SharedPrefsApi mPrefsApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        ActivityUserProfileBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_user_profile);

        mRealmApi = new RealmApi();
        mPrefsApi = SharedPrefsImpl.getInstance();

        mScrollView = (ScrollView) findViewById(R.id.sv_container);

        WorkaroundMapFragment mapFragment = (WorkaroundMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(latLng));
                    }
                });
            }
        });

        mapFragment.setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });

        mViewModel = new UserProfileViewModel(this, mapFragment);

        UserProfileContract.Presenter presenter = new UserProfilePresenter(mViewModel, mRealmApi, mPrefsApi);
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
