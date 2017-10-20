package vn.needy.vendor.screen.registerCompany;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.databinding.library.baseAdapters.BR;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
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
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = RegisterCompanyViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private RegisterCompanyContract.Presenter mPresenter;
    private GoogleApiClient googleApiClient;

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

    public RegisterCompanyViewModel(Context context, Navigator navigator, DialogManager dialogManager) {
        mContext = context;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        googleApiClient = new GoogleApiClient.Builder(mContext, this, this).addApi(LocationServices.API).build();
    }

    @Override
    public void onStart() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
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

    }

    @Override
    public void onSupportClick() {

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
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        mLat = (float) lastLocation.getLatitude();
        mLng = (float) lastLocation.getLongitude();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
}
