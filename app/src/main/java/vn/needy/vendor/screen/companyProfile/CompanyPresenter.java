package vn.needy.vendor.screen.companyProfile;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.api.v1.company.CompanyLocalDataSource;
import vn.needy.vendor.api.v1.company.CompanyRemoteDataSource;
import vn.needy.vendor.api.v1.company.CompanyRepository;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.service.VendorServiceClient;

/**
 * Created by truongpq on 07/12/2017.
 */

public class CompanyPresenter implements CompanyContract.Presenter {
    private CompanyContract.ViewModel mViewModel;

    private CompanyRepository mCompanyRepository;

    public CompanyPresenter(CompanyContract.ViewModel mViewModel, RealmApi realmApi) {
        this.mViewModel = mViewModel;
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(realmApi));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getCoverPictures() {
        List<Banner> banners = new ArrayList<>();
        banners.add(new RemoteBanner("https://techent.tv/wp-content/uploads/2015/11/Banner.jpg"));
        banners.add(new RemoteBanner("http://www.dubaimallsgroup.com/wp-content/uploads/2016/09/web-banner-eid-al-adha-1200-px-x-400-px-Ol.jpg"));
        banners.add(new RemoteBanner("https://spark.adobe.com/images/landing/examples/fathersday-sale-etsy-banner.jpg"));

        mViewModel.setBanners(banners);

    }

    @Override
    public void getCompanyInfo() {

    }
}
