package vn.needy.vendor.screen.companyProfile;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
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
import vn.needy.vendor.R;
import vn.needy.vendor.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.database.model.Company;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyViewModel extends BaseObservable implements CompanyContract.ViewModel{
    private CompanyContract.Presenter mPresenter;

    private Context mContext;
    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private Company mCompany;
    private String mNameError;
    private String mAddressError;

    private MapFragment mMapFragment;

    public CompanyViewModel(Context context, MapFragment mapFragment) {
        this.mContext = context;
        this.mMapFragment = mapFragment;
        mDrawableEdit = R.drawable.ic_edits_white;
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
    public void setPresenter(CompanyContract.Presenter presenter) {
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
           boolean isValidate = mPresenter.validateDataInput(mCompany.getName(), mCompany.getOfficeAddress());
           if (!isValidate) return;

            UpdateCompanyInfoRequest request = new UpdateCompanyInfoRequest();
            request.setName(mCompany.getName());
            request.setAddress(mCompany.getOfficeAddress());
            request.setDescription(mCompany.getDescription());
            request.setSiteURL(mCompany.getSiteUrl());
            request.setEmail(mCompany.getEmail());
            request.setFoundedDate(mCompany.getFoundedDate());
            request.setOpeningTime(mCompany.getOpeningTime());
            request.setClosingTime(mCompany.getClosingTime());
            request.setLat(mCompany.getLat());
            request.setLng(mCompany.getLng());
           mPresenter.updateCompanyInfo(mCompany.getId(), request);
        }

        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);

    }

    @Override
    public void setCompanyInfo(Company company) {
        mCompany = company;
        notifyPropertyChanged(BR.company);

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
}
