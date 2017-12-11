package vn.needy.vendor.screen.companyProfile;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;

/**
 * Created by truongpq on 07/12/2017.
 */

public class CompanyPresenter implements CompanyContract.Presenter {
    private CompanyContract.ViewModel mViewModel;

    private CompanyRepository mCompanyRepository;

    public CompanyPresenter(CompanyContract.ViewModel mViewModel, VendorApi vendorApi) {
        this.mViewModel = mViewModel;
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal()
        );
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
        String companyId = mCompanyRepository.getCompanyIdSync();
        mCompanyRepository.getCompanyInformation(companyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CompanyInfoResponse>() {
                    @Override
                    public void accept(CompanyInfoResponse companyResponse) throws Exception {
                        mViewModel.setCompanyInfo(companyResponse.getCompany(), companyResponse.getTotalStaff());
                    }
                });

//        mCompanyRepository.getCompanyInformation()
//                .subscribeOn(Schedulers.io())
//                .doOnError(new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                })
//                .map(new Function<CompanyResponse, Company>() {
//                    @Override
//                    public Company apply(CompanyResponse companyResponse) throws Exception {
//                        return companyResponse.getCompany();
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Company>() {
//                    @Override
//                    public void accept(Company company) throws Exception {
//                    }
//                }, new SafetyError() {
//                    @Override
//                    public void onSafetyError(BaseException error) {
//
//                    }
//                });
    }

    @Override
    public boolean validateDataInput(String name, String address) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(name)) {
            isValidate = false;
            mViewModel.onInputNameError("Tên hiển thị rỗng");
        }

        if (TextUtils.isEmpty(address)) {
            isValidate = false;
            mViewModel.onInputAddressError("Địa chỉ rỗng");
        }
        return isValidate;
    }

    @Override
    public void updateCompanyInfo(Company company) {
        UpdateCompanyInfoRequest request = new UpdateCompanyInfoRequest();
        request.setName(company.getName());
        request.setAddress(company.getOfficeAddress());
        request.setDescription(company.getDescription());
        request.setSiteURL(company.getSiteUrl());
        request.setEmail(company.getEmail());
        request.setFoundedDate(company.getFoundedDate());
        request.setOpeningTime(company.getOpeningTime());
        request.setClosingTime(company.getClosingTime());
        request.setLat(company.getLat());
        request.setLng(company.getLng());

        mCompanyRepository.updateCompanyInformation(company.getId(), request)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        // Show ProgressBar
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {

                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });

    }
}
