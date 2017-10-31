package vn.needy.vendor.screen.registerCompany;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
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
import com.google.firebase.iid.FirebaseInstanceId;

import vn.needy.vendor.R;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.screen.main.MainActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 07/10/2017.
 */

public class RegisterCompanyViewModel extends BaseObservable implements RegisterCompanyContract.ViewModel,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback, LocationListener {

    private static final String TAG = RegisterCompanyViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private RegisterCompanyContract.Presenter mPresenter;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private String mCompanyNameError;
    private String mOfficeAddressError;
    private String mStoreNameError;
    private String mStoreAddressError;

    private String mCompanyName;
    private String mOfficeAddress;
    private String mStoreName;
    private String mStoreAddress;

    private float mLat;
    private float mLng;

    private Drawable mCompanyNameDrawable;
    private Drawable mOfficeAddressDrawable;
    private Drawable mStoreNameDrawable;
    private Drawable mStoreAddressDrawable;

    private MapFragment mMapFragment;
    private Marker mCurrLocationMarker;

    public RegisterCompanyViewModel(Context context, Navigator navigator,
                                    DialogManager dialogManager, MapFragment mapFragment) {
        mContext = context;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mMapFragment = mapFragment;
        mGoogleApiClient = new GoogleApiClient.Builder(mContext, this, this).addApi(LocationServices.API).build();

        mCompanyNameDrawable = ContextCompat.getDrawable(context, R.drawable.ic_alert_circle_mini);
        mOfficeAddressDrawable = ContextCompat.getDrawable(context, R.drawable.ic_alert_circle_mini);
        mStoreNameDrawable = ContextCompat.getDrawable(context, R.drawable.ic_alert_circle_mini);
        mStoreAddressDrawable = ContextCompat.getDrawable(context, R.drawable.ic_alert_circle_mini);
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
    public void setPresenter(RegisterCompanyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputCompanyError(int errorMsg) {
        mCompanyNameError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.companyNameError);
    }

    @Override
    public void onInputOfficeAddressError(int errorMsg) {
        mOfficeAddressError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.officeAddressError);
    }

    @Override
    public void onInputStoreNameError(int errorMsg) {
        mStoreNameError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.storeNameError);
    }

    @Override
    public void onInputStoreAddressError(int errorMsg) {
        mStoreAddressError = mContext.getString(errorMsg);
        notifyPropertyChanged(BR.storeAddressError);
    }

    @Override
    public void onRegisterError(int errorMsg) {
        mNavigator.showToastCenterText(mContext.getString(errorMsg));
    }

    @Override
    public void onRegisterError(String message) {
        mNavigator.showToastCenterText(message);
    }

    @Override
    public void onRegisterSuccess() {
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }

    @Override
    public void onRegisterClick() {
        String deviceToken = FirebaseInstanceId.getInstance().getToken();

        RegisterCompanyRequest request = new RegisterCompanyRequest();
        request.setCompanyName(mCompanyName);
        request.setOfficeAddress(mOfficeAddress);
        request.setStoreName(mStoreName);
        request.setStoreAddress(mStoreAddress);
        request.setFcmToken(deviceToken);
        request.setLat(mLat);
        request.setLng(mLng);

        mPresenter.registerCompany(request);
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
    public void onShowProgressBar() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onBackPressed() {
        mNavigator.finishActivity();
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
    public void onMapReady(GoogleMap map) {
        LatLng mapCenter = new LatLng(mLat, mLng);
        mCurrLocationMarker = map.addMarker(new MarkerOptions().position(mapCenter));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 16));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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

    @Bindable
    public String getCompanyNameError() {
        return mCompanyNameError;
    }

    @Bindable
    public String getOfficeAddressError() {
        return mOfficeAddressError;
    }

    @Bindable
    public String getStoreNameError() {
        return mStoreNameError;
    }

    @Bindable
    public String getStoreAddressError() {
        return mStoreAddressError;
    }

    @Bindable
    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    @Bindable
    public String getOfficeAddress() {
        return mOfficeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        mOfficeAddress = officeAddress;
    }

    @Bindable
    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        mStoreName = storeName;
    }

    @Bindable
    public String getStoreAddress() {
        return mStoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        mStoreAddress = storeAddress;
    }

    @Bindable
    public Drawable getCompanyNameDrawable() {
        return mCompanyNameDrawable;
    }

    public void setCompanyNameDrawable(Drawable companyNameDrawable) {
        this.mCompanyNameDrawable = companyNameDrawable;
    }

    @Bindable
    public Drawable getOfficeAddressDrawable() {
        return mOfficeAddressDrawable;
    }

    public void setOfficeAddressDrawable(Drawable officeAddressDrawable) {
        this.mOfficeAddressDrawable = officeAddressDrawable;
    }

    @Bindable
    public Drawable getStoreNameDrawable() {
        return mStoreNameDrawable;
    }

    public void setStoreNameDrawable(Drawable storeNameDrawable) {
        this.mStoreNameDrawable = storeNameDrawable;
    }

    @Bindable
    public Drawable getStoreAddressDrawable() {
        return mStoreAddressDrawable;
    }

    public void setStoreAddressDrawable(Drawable storeAddressDrawable) {
        this.mStoreAddressDrawable = storeAddressDrawable;
    }
}
