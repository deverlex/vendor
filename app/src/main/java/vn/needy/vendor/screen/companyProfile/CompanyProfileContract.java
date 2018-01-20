package vn.needy.vendor.screen.companyProfile;

import java.util.List;

import io.realm.RealmList;
import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.domain.Company;
import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.company.context.CompanyContext;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyProfileContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void setBanners(List<Banner> banners);

        void onClickEdit();

        void setCompanyInfo(CompanyContext company, int numberOfCompany, int numberOfStore);

        void onInputNameError(String errorMsg);

        void onInputAddressError(String msg);

        void onBackPressed();

        void onClickFoundedDate();

        void onClickOpeningTime();

        void onClickClosingTime();

        void onClickDescription();

        void updateAddress(Place place);

        void onPlaceClick();
    }

    interface Presenter extends BasePresenter {
        void getCoverPictures();

        void getCompanyInfo();

        boolean validateDataInput(String name, String address);

        void updateCompanyInfo(CompanyContext company);
    }
}
