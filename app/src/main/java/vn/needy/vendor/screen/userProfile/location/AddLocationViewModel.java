package vn.needy.vendor.screen.userProfile.location;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import vn.needy.vendor.BR;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.screen.place.PlaceActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 19/01/2018.
 */

public class AddLocationViewModel extends BaseObservable implements AddLocationConstant.ViewModel{

    private Context mContext;
    private AddLocationConstant.Presenter mPresenter;
    private Navigator mNavigator;

    private String mName;
    private String mAddress;

    private String mNameError;
    private String mAddressError;

    public AddLocationViewModel(Context context, Navigator navigator) {
        mContext = context;
        mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(AddLocationConstant.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ((AddLocationActivity)mContext).onBackPressed();
    }

    @Override
    public void onClickComplete() {
        if (!mPresenter.validate(mName, mAddress)) {
            return;
        }

        mPresenter.getLatLng(mAddress);
    }

    @Override
    public void onInputNameError(int msg) {
        mNameError = mContext.getString(msg);
        notifyPropertyChanged(BR.nameError);
    }

    @Override
    public void onInputAddressError(int msg) {
        mAddressError = mContext.getString(msg);
        notifyPropertyChanged(BR.addressError);
    }

    @Override
    public void onPlaceClick() {
        mNavigator.startActivityForResult(PlaceActivity.class, AddLocationActivity.ADDRESS);
    }

    @Override
    public void updateAddress(Place place) {
        mAddress = place.getAddress();
        notifyPropertyChanged(BR.address);
    }

    @Override
    public void onGetLatLngCompleted(double lat, double lng) {
        UserLocationContext location = new UserLocationContext();
        location.setDescription(mName);
        location.setTitle(mAddress);
        location.setLat(lat);
        location.setLng(lng);
        setResult(location);
    }

    @Override
    public void onGetLatLngFailed() {
        UserLocationContext location = new UserLocationContext();
        location.setDescription(mName);
        location.setTitle(mAddress);
        setResult(location);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    @Bindable
    public String getAddress() {
        return mAddress;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    @Bindable
    public String getNameError() {
        return mNameError;
    }

    @Bindable
    public String getAddressError() {
        return mAddressError;
    }

    private void setResult(UserLocationContext location) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AddLocationActivity.LOCATION, location);
        intent.putExtras(bundle);
        mNavigator.finishActivity(PlaceActivity.RC_OK, intent);
    }

}
