package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.store.request.UpdateStoreInfoRequest;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResponse;

/**
 * Created by lion on 12/12/2017.
 */

public interface StoreData {

    interface Remote {
        Observable<ResponseWrapper<StoreInfoResponse>> getStoreInfo(String storeId);
        Observable<ResponseWrapper> updateStoreInfo(String storeId, UpdateStoreInfoRequest infoReq);
    }

    interface Local {
        Observable<Store> getOurCompanyAsync();

        void saveStoreSync(Store store);

        String getOurStoreIdSync();
    }
}
