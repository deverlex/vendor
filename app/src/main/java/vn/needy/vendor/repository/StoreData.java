package vn.needy.vendor.repository;

import vn.needy.vendor.model.Store;

/**
 * Created by lion on 12/12/2017.
 */

public interface StoreData {

    interface Remote {

    }

    interface Local {
        void saveStoreSync(Store store);
        String getOurStoreIdSync();
    }
}
