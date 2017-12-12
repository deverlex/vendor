package vn.needy.vendor.screen.companyProfile;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.model.FeeTransport;
import vn.needy.vendor.model.wrapper.FeeTransportWrapper;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResp;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;

/**
 * Created by truongpq on 07/12/2017.
 */

public class CompanyPresenter implements CompanyContract.Presenter {

    private static final String TAG = CompanyPresenter.class.getName();

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
        // Get data company from local
        mCompanyRepository.getOurCompanyAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Company>() {
                    @Override
                    public void accept(Company company) throws Exception {
                        // We need copy to other object when using model on viewModel update UI
                        // Error: cannot modify managed objects outside of a write transaction realm android
                        Company com = RealmApi.getSync().copyFromRealm(company);
                        mViewModel.setCompanyInfo(com, com.getTotalStaff());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });

        String companyId = mCompanyRepository.getCompanyIdSync();
        // get company from remote
        mCompanyRepository.getCompanyInformation(companyId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<CompanyInfoResp, Company>() {
                         @Override
                         public Company apply(CompanyInfoResp resp) throws Exception {
                             Company company = new Company(resp.getCompany());
                             // save total staff
                             company.setTotalStaff(resp.getTotalStaff());

                             // add list fee transport
                             RealmList<FeeTransport> feeTransports = new RealmList<>();
                             for (FeeTransportWrapper wrapper : resp.getFeeTransports()) {
                                 feeTransports.add(new FeeTransport(company.getId(), wrapper));
                             }
                             company.setFeeTransports(feeTransports);

                             mCompanyRepository.saveCompanySync(company);
                             return company;
                         }
                     }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Company>() {
                    @Override
                    public void accept(Company company) throws Exception {
                        mViewModel.setCompanyInfo(company, company.getTotalStaff());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        // need add notify error show on to screen

                    }
                });
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
        request.setAddress(company.getAddress());
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
