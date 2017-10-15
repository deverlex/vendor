package vn.needy.vendor.screen.registerUser;

import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import vn.needy.vendor.R;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.databinding.ActivityRegisterUserBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

public class RegisterUserActivity extends BaseActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = RegisterUserActivity.class.getName();

    private RegisterUserContract.ViewModel mViewModel;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_login));
        googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();

        Navigator navigator = new Navigator(this);
        //Clear data
        SharedPrefsImpl.getInstance().clear();
        VendorServiceClient.initialize(getApplication());

        mViewModel = new RegisterUserViewModel(this, navigator);

        RegisterUserContract.Presenter presenter = new RegisterUserPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityRegisterUserBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register_user);
        binding.setViewModel((RegisterUserViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        Log.w(TAG, "lat: " + lastLocation.getLatitude());
        Log.w(TAG, "lng: " + lastLocation.getLongitude());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed");
    }
}
