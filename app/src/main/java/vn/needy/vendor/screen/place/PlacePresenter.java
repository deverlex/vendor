package vn.needy.vendor.screen.place;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vn.needy.vendor.model.Place;

/**
 * Created by truongpq on 12/01/2018.
 */

public class PlacePresenter implements PlaceConstant.Presenter {

    private PlaceConstant.ViewModel mViewModel;

    private Context mContext;
    private PlaceDetectionClient mPlaceDetectionClient;
    private Geocoder mGeocoder;

    public PlacePresenter(Context context, PlaceConstant.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mPlaceDetectionClient = Places.getPlaceDetectionClient(mContext, null);
        mGeocoder = new Geocoder(mContext, Locale.getDefault());
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getSuggestPlaces() {
        mPlaceDetectionClient.getCurrentPlace(null).addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                List<Place> places = new ArrayList<>();
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Place place = new Place();
                    place.setId(placeLikelihood.getPlace().getId());
                    place.setName(placeLikelihood.getPlace().getName().toString());
                    place.setAddress(placeLikelihood.getPlace().getAddress().toString());
                    place.setLat(placeLikelihood.getPlace().getLatLng().latitude);
                    place.setLng(placeLikelihood.getPlace().getLatLng().longitude);
                    places.add(place);
                }
                mViewModel.setPlaces(places);
            }
        });
    }

    @Override
    public void getPlaceByLocation(LatLng latLng) {
        try {
            Address address = mGeocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0);
            StringBuilder addr = new StringBuilder();
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addr.append(address.getAddressLine(i));
            }

            Place place = new Place();
            place.setAddress(addr.toString());
            place.setName(address.getFeatureName());
            place.setLat(address.getLatitude());
            place.setLng(address.getLongitude());
            mViewModel.onChangePinPlace(place);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
