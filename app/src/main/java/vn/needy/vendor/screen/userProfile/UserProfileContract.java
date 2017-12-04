package vn.needy.vendor.screen.userProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
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
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures();
    }
}
