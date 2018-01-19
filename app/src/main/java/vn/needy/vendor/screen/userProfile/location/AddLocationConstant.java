package vn.needy.vendor.screen.userProfile.location;

import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 19/01/2018.
 */

public interface AddLocationConstant {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onBackPressed();

        void onClickComplete();

        void onInputNameError(int msg);

        void onInputAddressError(int msg);

        void onPlaceClick();

        void updateAddress(Place place);

        void onGetLatLngCompleted(double lat, double lng);

        void onGetLatLngFailed();
    }

    interface Presenter extends BasePresenter {
        boolean validate(UserLocationContext location);

        void getLatLng(String address);
    }
}
