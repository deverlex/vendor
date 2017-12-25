package vn.needy.vendor.repository.local;

import android.util.Log;

import io.realm.Realm;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.model.Store;
import vn.needy.vendor.repository.StoreData;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreDataLocal implements StoreData.Local {

    private static final String TAG = StoreDataLocal.class.getName();

    @Override
    public void saveStoreSync(final Store store) {
        RealmApi.getSync().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(store);
                Log.w(TAG, "INSERT COMPANY SUCCESS.................OOOOOT");
            }
        });
    }

    @Override
    public String getOurStoreIdSync() {
        return RealmApi.getSync().where(Store.class)
                .findFirst().getId();
    }
}
