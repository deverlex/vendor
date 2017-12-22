package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.model.Store;

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

    public void saveStoreSync(Store store) {
        mLocal.saveStoreSync(store);
    }
}
