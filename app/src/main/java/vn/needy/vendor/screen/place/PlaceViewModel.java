package vn.needy.vendor.screen.place;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.os.Bundle;


import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 12/01/2018.
 */

public class PlaceViewModel extends BaseObservable implements PlaceConstant.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Place>,
        OnMapReadyCallback{

    private PlaceConstant.Presenter mPresenter;
    private Context mContext;

    private PlacesAdapter mPlacesAdapter;
    private SupportMapFragment mMapFragment;

    private Marker mCenterMarker;
    private Place mPinPlace;
    private boolean mIsVisibleMap;
    private Navigator mNavigator;

    public PlaceViewModel(Context context, Navigator navigator, PlacesAdapter placesAdapter, SupportMapFragment mapFragment) {
        mContext = context;
        mPlacesAdapter = placesAdapter;
        mPlacesAdapter.setItemClickListener(this);
        mMapFragment = mapFragment;
        mMapFragment.getMapAsync(this);
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.getSuggestPlaces();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(PlaceConstant.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setPlaces(List<Place> places) {
        mPlacesAdapter.setData(places);
    }

    @Override
    public void onTextChanged(CharSequence query) {
        if (!query.toString().isEmpty() && !mIsVisibleMap) {
            mPlacesAdapter.getFilter().filter(query);
        }
    }

    @Override
    public void onBackPressed() {
        if (mIsVisibleMap) {
            mIsVisibleMap = false;
            notifyPropertyChanged(BR.visibleMap);

            mPinPlace = null;
            notifyPropertyChanged(BR.pinPlace);
        } else {
            ((PlaceActivity)mContext).onBackPressed();
        }
    }

    @Override
    public void onChangePinPlace(Place place) {
        mPinPlace = place;
        notifyPropertyChanged(BR.pinPlace);
    }

    @Override
    public void onShowMap() {
        mIsVisibleMap = true;
        notifyPropertyChanged(BR.visibleMap);
    }

    @Override
    public void clickAcceptPlace() {
        setResult(mPinPlace);
    }

    @Bindable
    public PlacesAdapter getPlacesAdapter() {
        return mPlacesAdapter;
    }

    @Bindable
    public String getPinPlace() {
        if (mPinPlace != null) {
            return mPinPlace.getAddress();
        } else {
            return "";
        }
    }

    @Bindable
    public boolean isVisibleMap() {
        return mIsVisibleMap;
    }

    @Override
    public void onItemRecyclerViewClick(Place item) {
        setResult(item);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);

        LocationServices.getFusedLocationProviderClient(mContext).getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(location.getLatitude(), location.getLongitude()), 16));
                        }
                    }
                });

        mCenterMarker = googleMap.addMarker(new MarkerOptions().
                position(googleMap.getCameraPosition().target)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_pin)));

        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                mCenterMarker.setPosition(googleMap.getCameraPosition().target);
            }
        });

        googleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                mPinPlace = null;
                notifyPropertyChanged(BR.pinPlace);
            }
        });

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mPresenter.getPlaceByLocation(googleMap.getCameraPosition().target);
            }
        });
    }

    private void setResult(Place place) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PlaceActivity.PLACE, place);
        intent.putExtras(bundle);
        mNavigator.finishActivity(PlaceActivity.RC_OK, intent);
    }
}
