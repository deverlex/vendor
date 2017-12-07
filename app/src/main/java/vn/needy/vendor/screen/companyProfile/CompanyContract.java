package vn.needy.vendor.screen.companyProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void setBanners(List<Banner> banners);

        void onClickEdit();

        void setCompanyInfo(Company company);
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures();

        void getCompanyInfo();
    }
}
