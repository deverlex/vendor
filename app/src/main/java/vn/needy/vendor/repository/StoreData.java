package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.model.Store;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.remote.store.response.StoreInfoResp;

/**
 * Created by lion on 12/12/2017.
 */

public interface StoreData {

    interface Remote {
        Observable<ResponseWrapper<StoreInfoResp>> getStoreInfo(String storeId);
    }

    interface Local {
        Observable<Store> getOurCompanyAsync();

        void saveStoreSync(Store store);

        String getOurStoreIdSync();
    }
}
