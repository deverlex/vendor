package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.model.Store;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.store.request.UpdateStoreInfoReq;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResp;

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

    public Observable<ResponseWrapper<StoreInfoResp>> getStoreInfo(String storeId) {
        return mRemote.getStoreInfo(storeId);
    }

    public Observable<ResponseWrapper> updateStoreInfo(String storeId, UpdateStoreInfoReq infoReq) {
        return mRemote.updateStoreInfo(storeId, infoReq);
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
}
