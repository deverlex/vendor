package vn.needy.vendor.screen.storeProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.repository.remote.store.context.StoreContext;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 12/12/2017.
 */

public interface StoreProfileContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void setBanner(List<Banner> banners);

        void onClickEdit();

        void setStoreInfo(StoreContext store, int numberOfEmployee);

        void setAvatar(String avatarUrl);

        void onClickDescription();

        void onBackPressed();

        void onClickOpeningTime();

        void onClickClosingTime();

        void onInputNameError(String msg);

        void onInputAddressError(String msg);
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures(List<Long> images);
        void getStoreInfo();
        void updateStoreInfo(Store store);
        boolean validateDataInput(String name, String address);
    }
}
