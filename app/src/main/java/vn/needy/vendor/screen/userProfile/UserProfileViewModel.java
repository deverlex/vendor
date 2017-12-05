package vn.needy.vendor.screen.userProfile;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.database.model.User;
import vn.needy.vendor.screen.userProfile.changePassword.ChangePasswordFragment;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileViewModel extends BaseObservable implements UserProfileContract.ViewModel {

    private UserProfileContract.Presenter mPresenter;

    private User mUser;
    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private MapFragment mMapFragment;
    private Context mContext;

    public UserProfileViewModel(Context context, MapFragment mapFragment) {
        this.mContext = context;
        this.mMapFragment = mapFragment;
        mDrawableEdit = R.drawable.ic_edits_white;

        // load cover pictures
    }

    @Override
    public void onStart() {
        mPresenter.getCoverPictures();
        mPresenter.getUserInfo();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(UserProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClickEdit() {
        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
    }

    @Override
    public void setBanners(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onPositionClick() {
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMap.clear();
                LocationServices.getFusedLocationProviderClient(mContext)
                        .getLastLocation().
                        addOnSuccessListener((Activity) mContext, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
                                googleMap.addMarker(new MarkerOptions().position(currentPosition));
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 10));
                            }
                        });
            }
        });
    }

    @Override
    public void onChangePassword() {
        ((UserProfileActivity) mContext).initFragment(android.R.id.content,
                ChangePasswordFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        ((UserProfileActivity) mContext).onBackPressed();
    }

    @Override
    public void setUserInfo(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public boolean getEnable() {
        return mEnable;
    }

    @Bindable
    public int getDrawableEdit() {
        return mDrawableEdit;
    }

    @Bindable
    public User getUser() {
        return mUser;
    }
}
