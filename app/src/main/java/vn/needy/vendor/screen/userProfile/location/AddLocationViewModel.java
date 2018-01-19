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

    private UserLocationContext mLocation;
    private int mPosition;

    private String mNameError;
    private String mAddressError;

    public AddLocationViewModel(Context context, Navigator navigator, UserLocationContext location, int position) {
        mContext = context;
        mNavigator = navigator;
        mLocation = location;
        mPosition = position;
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
        if (!mPresenter.validate(mLocation)) {
            return;
        }

        mPresenter.getLatLng(mLocation.getDescription());
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
         mLocation.setDescription(place.getAddress());
        notifyPropertyChanged(BR.location);
    }

    @Override
    public void onGetLatLngCompleted(double lat, double lng) {
        mLocation.setLat(lat);
        mLocation.setLng(lng);
        setResult(mLocation);
    }

    @Override
    public void onGetLatLngFailed() {
        setResult(mLocation);
    }

    @Override
    public void onDeleteClick() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(AddLocationActivity.LOCATION_POSITION, mPosition);
        intent.putExtras(bundle);
        mNavigator.finishActivity(AddLocationActivity.RC_DELETE, intent);
    }

    @Bindable
    public UserLocationContext getLocation() {
        return mLocation;
    }

    public void setLocation(UserLocationContext location) {
        mLocation = location;
    }

    @Bindable
    public String getNameError() {
        return mNameError;
    }

    @Bindable
    public String getAddressError() {
        return mAddressError;
    }

    @Bindable
    public int getPosition() {
        return mPosition;
    }

    private void setResult(UserLocationContext location) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AddLocationActivity.LOCATION, location);
        if (mPosition == -1) {
            // Add new location
            intent.putExtras(bundle);
            mNavigator.finishActivity(AddLocationActivity.RC_OK, intent);
        } else {
            // Edit location
            bundle.putInt(AddLocationActivity.LOCATION_POSITION, mPosition);
            intent.putExtras(bundle);
            mNavigator.finishActivity(AddLocationActivity.RC_OK_EDIT, intent);
        }
    }

}
