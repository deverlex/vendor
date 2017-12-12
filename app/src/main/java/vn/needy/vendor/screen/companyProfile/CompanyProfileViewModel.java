package vn.needy.vendor.screen.companyProfile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.databinding.library.baseAdapters.BR;
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
import vn.needy.vendor.R;
import vn.needy.vendor.model.Company;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyProfileViewModel extends BaseObservable implements CompanyProfileContract.ViewModel{
    private CompanyProfileContract.Presenter mPresenter;

    private Context mContext;
    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private Company mCompany;
    private int mStaffCount;
    private String mNameError;
    private String mAddressError;
    private boolean mVisibleDescription;
    private int mDrawableExpandDescription;

    private MapFragment mMapFragment;

    public CompanyProfileViewModel(Context context, MapFragment mapFragment) {
        this.mContext = context;
        this.mMapFragment = mapFragment;
        mDrawableEdit = R.drawable.ic_edits_white;
        mDrawableExpandDescription = R.drawable.ic_next_right;
    }

    @Override
    public void onStart() {
        mPresenter.getCoverPictures();
        mPresenter.getCompanyInfo();

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
                        mCompany.setLat((float) latLng.latitude);
                        mCompany.setLng((float) latLng.longitude);
                    }
                });
            }
        });

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(CompanyProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setBanners(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onClickEdit() {
        if (mEnable) {
           boolean isValidate = mPresenter.validateDataInput(mCompany.getName(), mCompany.getAddress());
           if (!isValidate) return;

           mPresenter.updateCompanyInfo(mCompany);
        }

        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);

    }

    @Override
    public void setCompanyInfo(Company company, int totalStaff) {
        mCompany = company;
        mStaffCount = totalStaff;
        notifyPropertyChanged(BR.company);
        notifyPropertyChanged(BR.staffCount);

        //Move to address on Map
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // Move to Address
                googleMap.clear();
                LatLng addressLatLng = new LatLng(mCompany.getLat(), mCompany.getLng());
                googleMap.addMarker(new MarkerOptions().position(addressLatLng));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(addressLatLng, 13));
            }
        });

    }

    @Override
    public void onInputNameError(String errorMsg) {
        mNameError = errorMsg;
        notifyPropertyChanged(BR.nameError);
    }

    @Override
    public void onInputAddressError(String msg) {
        mAddressError = msg;
        notifyPropertyChanged(BR.addressError);
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
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
                                LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
                                googleMap.addMarker(new MarkerOptions().position(currentPosition));
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
                                mCompany.setLat((float) location.getLatitude());
                                mCompany.setLng((float) location.getLongitude());
                            }
                        });
            }
        });

    }

    @Override
    public void onClickFoundedDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                mCompany.setFoundedDate(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.company);
            }
        }, 2017, 11, 6);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }

    @Override
    public void onClickOpeningTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = new GregorianCalendar(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mCompany.setOpeningTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.company);
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
                mCompany.setClosingTime(dateFormat.format(calendar.getTime()));
                notifyPropertyChanged(BR.company);
            }
        }, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onClickDescription() {
        mVisibleDescription = !mVisibleDescription;
        notifyPropertyChanged(BR.visibleDescription);

        mDrawableExpandDescription = mVisibleDescription ? R.drawable.ic_expand : R.drawable.ic_next_right;
        notifyPropertyChanged(BR.drawableExpandDescription);
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
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
    public Company getCompany() {
        return mCompany;
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
    public boolean isVisibleDescription() {
        return mVisibleDescription;
    }

    @Bindable
    public int getDrawableExpandDescription() {
        return mDrawableExpandDescription;
    }

    @Bindable
    public int getStaffCount() {
        return mStaffCount;
    }
}
