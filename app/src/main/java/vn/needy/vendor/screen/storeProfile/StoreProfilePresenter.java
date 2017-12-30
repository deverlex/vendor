package vn.needy.vendor.screen.storeProfile;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.StoreRepository;
import vn.needy.vendor.repository.local.StoreDataLocal;
import vn.needy.vendor.repository.remote.store.StoreDataRemote;
import vn.needy.vendor.repository.remote.store.request.UpdateStoreInfoReq;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResp;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfilePresenter implements StoreProfileContract.Presenter {

    private StoreProfileContract.ViewModel mViewModel;

    private StoreRepository mStoreRepository;

    public StoreProfilePresenter(StoreProfileContract.ViewModel viewModel, VendorApi vendorApi) {
        mViewModel = viewModel;
        mStoreRepository = new StoreRepository(new StoreDataRemote(vendorApi), new StoreDataLocal());
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

        mViewModel.setBanner(banners);
    }

    @Override
    public void getStoreInfo() {
        getStoreFromLocal();
        getStoreFromRemote();
    }

    @Override
    public void updateStoreInfo(Store store) {
        UpdateStoreInfoReq infoReq = new UpdateStoreInfoReq(store);
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

    private void getStoreFromLocal() {
        mStoreRepository.getOurStoreAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Store>() {
                    @Override
                    public void accept(Store store) throws Exception {
                        Store st = RealmApi.getSync().copyFromRealm(store);
                        mViewModel.setStoreInfo(st);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
    }

    private void getStoreFromRemote() {
        String storeId = mStoreRepository.getOurStoreIdSync();
        mStoreRepository.getStoreInfo(storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseWrapper<StoreInfoResp>, Store>() {
                    @Override
                    public Store apply(ResponseWrapper<StoreInfoResp> storeInfoRespResponseWrapper) throws Exception {
                        StoreInfoResp storeInfoResp = storeInfoRespResponseWrapper.getData();
                        if (storeInfoResp != null) {
                            Store store = new Store(storeInfoResp.getStore());
                            store.setTotalStaff(storeInfoResp.getTotalStaff());
                            mStoreRepository.saveStoreSync(store);
                            return store;
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Store>() {
                    @Override
                    public void accept(Store store) throws Exception {
                        mViewModel.setStoreInfo(store);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
    }
}
