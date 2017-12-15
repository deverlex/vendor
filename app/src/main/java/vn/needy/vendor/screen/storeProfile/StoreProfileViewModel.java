package vn.needy.vendor.screen.storeProfile;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.util.Log;
import android.widget.TimePicker;

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
import vn.needy.vendor.model.Store;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfileViewModel extends BaseObservable implements StoreProfileContract.ViewModel {

    private Context mContext;
    private StoreProfileContract.Presenter mPresenter;

    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private Store mStore;
    private boolean mVisibleDescription;
    private int mDrawableExpandDescription;
    private MapFragment mMapFragment;

    StoreProfileViewModel(Context context, MapFragment mapFragment) {
        mContext = context;
        mMapFragment = mapFragment;
        mDrawableEdit = R.drawable.ic_edits_white;
        mDrawableExpandDescription = R.drawable.ic_next_right;
    }

    @Override
    public void onStart() {
        mPresenter.getCoverPictures();
        mPresenter.getStoreInfo();

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
                        mStore.setLat((float) latLng.latitude);
                        mStore.setLng((float) latLng.longitude);
                    }
                });
            }
        });
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(StoreProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
    }

    @Bindable
    public int getDrawableEdit() {
        return mDrawableEdit;
    }

    @Bindable
    public Store getStore() {
        return mStore;
    }

    @Bindable
    public boolean isVisibleDescription() {
        return mVisibleDescription;
    }

    @Bindable
    public int getDrawableExpandDescription() {
        return mDrawableExpandDescription;
    }

    @Bindable
    public boolean isEnable() {
        return mEnable;
    }

    @Override
    public void setBanner(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onClickEdit() {
        if (mEnable) {
            // Update
        }

        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);
    }

    @Override
    public void setStoreInfo(Store store) {
        mStore = store;
        notifyPropertyChanged(BR.store);

        //Move to address on Map
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // Move to Address
                googleMap.clear();
                LatLng addressLatLng = new LatLng(mStore.getLat(), mStore.getLng());
                googleMap.addMarker(new MarkerOptions().position(addressLatLng));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(addressLatLng, 13));
            }
        });
    }

    @Override
    public void onClickDescription() {
        mVisibleDescription = !mVisibleDescription;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.visibleDescription);

        mDrawableExpandDescription = mVisibleDescription ? R.drawable.ic_expand : R.drawable.ic_next_right;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.drawableExpandDescription);
    }

    @Override
    public void onClickPosition() {
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMap.clear();
                LocationServices.getFusedLocationProviderClient(mContext)
                        .getLastLocation().
                        addOnSuccessListener((Activity) mContext, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location!= null) {
                                    LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
                                    googleMap.addMarker(new MarkerOptions().position(currentPosition));
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
                                    mStore.setLat((float) location.getLatitude());
                                    mStore.setLng((float) location.getLongitude());
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onClickOpeningTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mStore.setOpeningTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.store);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onClickClosingTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mStore.setClosingTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.store);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }
}
