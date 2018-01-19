package vn.needy.vendor.screen.userProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.user.context.UserContext;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.domain.User;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickEdit();

        void setBanners(List<Banner> banners);

        void onPositionClick();

        void onChangePassword();

        void onBackPressed();

        void onClickBirthday();

        void setUserInfo(UserContext user);

        void setUserLocations(List<UserLocationContext> userLocations);

        void onClickExpandLocation();

        void onSpinnerItemSelected(int position);

        void onClickAddress();

        void updateCompanyAddress(Place place);

        void onClickAddLocation();

        void addLocation(UserLocationContext location);
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures(List<Long> images);
        void getUserInfo();
        void updateUserInformation(UpdateUserInfoRequest request);
    }
}
