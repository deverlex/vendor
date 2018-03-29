package vn.needy.vendor.screen.companyProfile;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmList;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.port.message.BaseCode;
import vn.needy.vendor.port.wrapper.FeeTransportWrapper;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.company.context.CompanyContext;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.domain.Company;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;

/**
 * Created by truongpq on 07/12/2017.
 */

public class CompanyProfilePresenter implements CompanyProfileContract.Presenter {

    private static final String TAG = CompanyProfilePresenter.class.getName();

    private Context mContext;
    private CompanyProfileContract.ViewModel mViewModel;

    private CompanyRepository mCompanyRepository;

    private Geocoder mGeocoder;

    public CompanyProfilePresenter(Context context, CompanyProfileContract.ViewModel mViewModel, VendorApi vendorApi) {
        mContext = context;
        this.mViewModel = mViewModel;
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal(SharedPrefsImpl.getInstance())
        );

        mGeocoder = new Geocoder(context);
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
        // get company from remote
        getCompanyInfoFromRemote();
    }

    private void getCompanyInfoFromRemote() {
        long companyId = mCompanyRepository.getCompanyId();

        mCompanyRepository.getCompanyInfo(companyId)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<CompanyInfoResponse>>() {
                    @Override
                    public void accept(ResponseWrapper<CompanyInfoResponse> respone) throws Exception {
                        if (respone.getCode() == BaseCode.OK && respone.getData() != null) {
                            CompanyInfoResponse data = respone.getData();
                            mViewModel.setCompanyInfo(data.getCompany(),
                                    data.getNumberEmployee(),
                                    data.getNumberStore());
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

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
    public void updateCompanyInfo(CompanyContext company) {
        UpdateCompanyInfoRequest request = new UpdateCompanyInfoRequest();
        request.setName(company.getName());
        request.setAddress(company.getAddress());
        request.setDescription(company.getDescription());
        request.setSiteUrl(company.getSiteUrl());
        request.setFoundedDate(company.getFoundedDate());
        request.setEmail(company.getEmail());
        request.setOpeningTime(company.getOpeningTime());
        request.setClosingTime(company.getClosingTime());

        try {
            List<Address> addresses = mGeocoder.getFromLocationName(company.getAddress(), 1);
            if (addresses != null && addresses.size() > 0) {
                request.setLat(addresses.get(0).getLatitude());
                request.setLng(addresses.get(0).getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long companyId = mCompanyRepository.getCompanyId();
        mCompanyRepository.updateCompanyInfo(companyId, request)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        // Show ProgressBar
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper>() {
                    @Override
                    public void accept(ResponseWrapper baseResponse) throws Exception {

                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
    }
}
