package vn.needy.vendor.screen.storeProfile;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.message.BaseCode;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.StoreRepository;
import vn.needy.vendor.repository.local.StoreDataLocal;
import vn.needy.vendor.repository.remote.store.StoreDataRemote;
import vn.needy.vendor.repository.remote.store.request.UpdateStoreInfoRequest;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResponse;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfilePresenter implements StoreProfileContract.Presenter {

    private StoreProfileContract.ViewModel mViewModel;

    private StoreRepository mStoreRepository;

    public StoreProfilePresenter(StoreProfileContract.ViewModel viewModel, VendorApi vendorApi) {
        mViewModel = viewModel;
        mStoreRepository = new StoreRepository(new StoreDataRemote(vendorApi), new StoreDataLocal(SharedPrefsImpl.getInstance()));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getCoverPictures(List<Long> images) {
        List<Banner> banners = new ArrayList<>();
        if (images != null) {
            for (Long image : images) {
                banners.add(new RemoteBanner(String.format(Locale.getDefault(), "%sv1/images/products/%d", Constant.API_END_POINT_URL, image)));
            }
        }

        mViewModel.setBanner(banners);
    }

    @Override
    public void getStoreInfo() {
        getStoreFromRemote();
    }

    @Override
    public void updateStoreInfo(Store store) {
        UpdateStoreInfoRequest infoReq = new UpdateStoreInfoRequest(store);
        mStoreRepository.updateStoreInfo(store.getId(), infoReq)
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


    private void getStoreFromRemote() {
        long storeId = mStoreRepository.getStoreId();
        mStoreRepository.getStoreInfo(storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<StoreInfoResponse>>() {
                    @Override
                    public void accept(ResponseWrapper<StoreInfoResponse> respone) throws Exception {
                        if (respone.getCode() == BaseCode.OK && respone.getData() != null) {
                            StoreInfoResponse data = respone.getData();
                            mViewModel.setStoreInfo(data.getStore(), data.getNumberOfEmployee());
                            mViewModel.setAvatar(String.format(Locale.getDefault(), "%sv1/images/products/%d", Constant.API_END_POINT_URL, data.getAvatar()));
                            getCoverPictures(data.getImages());
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
    }
}
