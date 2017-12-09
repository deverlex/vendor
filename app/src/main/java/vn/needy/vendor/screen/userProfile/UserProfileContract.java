package vn.needy.vendor.screen.userProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.datasource.model.User;
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

        void onClickMale();

        void onClickFemale();

        void setUserInfo(User user);
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures();
        void getUserInfo();
        void updateUserInformation(UpdateUserInfoRequest request);
    }
}
