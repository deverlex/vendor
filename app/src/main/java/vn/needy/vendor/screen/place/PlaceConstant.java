package vn.needy.vendor.screen.place;


import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import vn.needy.vendor.model.Place;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 12/01/2018.
 */

public interface PlaceConstant {
    interface ViewModel extends BaseViewModel<Presenter> {
        void setPlaces(List<Place> places);

        void onTextChanged(CharSequence query);

        void onBackPressed();

        void onChangePinPlace(Place place);

        void onShowMap();

        void clickAcceptPlace();
    }

    interface Presenter extends BasePresenter {
        void getSuggestPlaces();

        void getPlaceByLocation(LatLng latLng);
    }
}
