package vn.needy.vendor.screen.userProfile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.widget.DatePicker;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.model.User;
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

        // Settup click map
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                // Settup click
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(latLng));
                        mUser.setLat((float) latLng.latitude);
                        mUser.setLng((float) latLng.longitude);
                    }
                });
            }
        });
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
        if (mEnable) {
            UpdateUserInfoRequest request = new UpdateUserInfoRequest();
            request.setAddress(mUser.getAddress());
            request.setEmail(mUser.getEmail());
            request.setName(mUser.getFullName());
            request.setBirthday(mUser.getBirthday());
            request.setGender(mUser.getGender());
            request.setLat(mUser.getLat());
            request.setLng(mUser.getLng());
            mPresenter.updateUserInformation(request);
        }

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
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
                                mUser.setLat((float) location.getLatitude());
                                mUser.setLng((float) location.getLongitude());
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
    public void onClickBirthday() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                mUser.setBirthday(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.user);
            }
        }, 2017, 11, 6);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }

    @Override
    public void onClickMale() {
        mUser.setGender("male");
        notifyPropertyChanged(BR.user);
    }

    @Override
    public void onClickFemale() {
        mUser.setGender("female");
        notifyPropertyChanged(BR.user);
    }

    @Override
    public void setUserInfo(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);

        //Move to address on Map
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // Move to Address
                googleMap.clear();
                LatLng addressLatLng = new LatLng(mUser.getLat(), mUser.getLng());
                googleMap.addMarker(new MarkerOptions().position(addressLatLng));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(addressLatLng, 13));
            }
        });
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
