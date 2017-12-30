package vn.needy.vendor.screen.storeProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 12/12/2017.
 */

public interface StoreProfileContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void setBanner(List<Banner> banners);

        void onClickEdit();

        void setStoreInfo(Store store);

        void onClickDescription();

        void onClickPosition();

        void onBackPressed();

        void onClickOpeningTime();

        void onClickClosingTime();

        void onInputNameError(String msg);

        void onInputAddressError(String msg);
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures();
        void getStoreInfo();
        void updateStoreInfo(Store store);
        boolean validateDataInput(String name, String address);
    }
}
