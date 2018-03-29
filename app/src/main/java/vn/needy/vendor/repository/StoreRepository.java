package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.store.request.CreateStoreRequest;
import vn.needy.vendor.repository.remote.store.request.UpdateStoreInfoRequest;
import vn.needy.vendor.repository.remote.store.response.CreateStoreRespone;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResponse;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreRepository {

    private StoreData.Remote mRemote;
    private StoreData.Local mLocal;

    public StoreRepository(StoreData.Remote remote, StoreData.Local local) {
        mRemote = remote;
        mLocal = local;
    }

    public Observable<ResponseWrapper<StoreInfoResponse>> getStoreInfo(long storeId) {
        return mRemote.getStoreInfo(storeId);
    }

    public Observable<ResponseWrapper> updateStoreInfo(String storeId, UpdateStoreInfoRequest infoReq) {
        return mRemote.updateStoreInfo(storeId, infoReq);
    }

    public Observable<ResponseWrapper<CreateStoreRespone>> createStore(long companyId, CreateStoreRequest request) {
        return mRemote.createStore(companyId, request);
    }

    /** LOCAL */
    public Observable<Store> getOurStoreAsync() {
        return mLocal.getOurCompanyAsync();
    }

    public void saveStoreSync(Store store) {
        mLocal.saveStoreSync(store);
    }

    public String getOurStoreIdSync() {
        return mLocal.getOurStoreIdSync();
    }

    public void saveStoreId(long storeId) {
        mLocal.saveStoreId(storeId);
    }

    public long getStoreId() {
        return mLocal.getStoreId();
    }
}
