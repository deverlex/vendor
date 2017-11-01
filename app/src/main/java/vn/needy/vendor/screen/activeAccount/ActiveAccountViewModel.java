package vn.needy.vendor.screen.activeAccount;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.needy.vendor.BR;
import vn.needy.vendor.data.source.remote.api.request.ActiveAccountRequest;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 31/10/2017.
 */

public class ActiveAccountViewModel extends BaseObservable implements ActiveAccountContract.ViewModel,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback, LocationListener {

    private static final String TAG = ActiveAccountViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private ActiveAccountContract.Presenter mPresenter;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private String mFullNameError;
    private String mAddressError;
    private String mFullName;
    private String mAddress;

    private float mLng;
    private float mLat;

    private MapFragment mMapFragment;
    private Marker mCurrLocationMarker;

    public ActiveAccountViewModel(Context context, Navigator navigator,
                                  DialogManager dialogManager, MapFragment mapFragment) {
        mContext = context;
        mNavigator = navigator;
        mDialogManager = dialogManager;

        mMapFragment = mapFragment;
        mGoogleApiClient = new GoogleApiClient
                .Builder(mContext, this, this)
                .addApi(LocationServices.API).build();
    }

    @Override
    public void onStart() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void setPresenter(ActiveAccountContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputFullNameError(int msg) {
        mFullNameError = mContext.getString(msg);
        notifyPropertyChanged(BR.fullNameError);
    }

    @Override
    public void onInputAddressError(int msg) {
        mAddressError = mContext.getString(msg);
        notifyPropertyChanged(BR.addressError);
    }

    @Override
    public void onActiveClick() {
        ActiveAccountRequest request = new ActiveAccountRequest();
        request.setFullName(mFullName);
        request.setAddress(mAddress);
        request.setLat(mLat);
        request.setLng(mLng);
        mPresenter.activeAccount(request);
    }

    @Override
    public void onPositionClick() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(3000); //5 seconds
        mLocationRequest.setFastestInterval(2000); //3 seconds
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onActiveSuccess() {
        mNavigator.onBackPressed();
    }

    @Override
    public void onActiveError(String msg) {
        mNavigator.showToastCenterText(msg);
    }

    @Override
    public void onBackPressed() {
        mNavigator.onBackPressed();
    }

    @Override
    public void onShowProgressBar() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (lastLocation != null) {
            mLat = (float) lastLocation.getLatitude();
            mLng = (float) lastLocation.getLongitude();
            mMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location location) {
        mLat = (float) location.getLatitude();
        mLng = (float) location.getLongitude();
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng mapCenter = new LatLng(mLat, mLng);
        mCurrLocationMarker = map.addMarker(new MarkerOptions().position(mapCenter));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 16));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Bindable
    public String getFullNameError() {
        return mFullNameError;
    }

    @Bindable
    public String getAddressError() {
        return mAddressError;
    }

    @Bindable
    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
