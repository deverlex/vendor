package vn.needy.vendor.screen.storeProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.model.Store;
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
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures();
        void getStoreInfo();
    }
}
