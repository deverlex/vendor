package vn.needy.vendor.screen.companyProfile;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.api.v1.company.request.UpdateCompanyInfoRequest;
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

        void onInputNameError(String errorMsg);

        void onInputAddressError(String msg);

        void onBackPressed();

        void onClickPosition();
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures();

        void getCompanyInfo();

        boolean validateDataInput(String name, String address);

        void updateCompanyInfo(String companyId, UpdateCompanyInfoRequest infoRequest);
    }
}
