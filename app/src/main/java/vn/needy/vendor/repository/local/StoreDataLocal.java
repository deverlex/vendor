package vn.needy.vendor.repository.local;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.domain.Store;
import vn.needy.vendor.repository.StoreData;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreDataLocal implements StoreData.Local {

    private static final String TAG = StoreDataLocal.class.getName();

    private SharedPrefsApi mPrefsApi;

    public StoreDataLocal() {

    }

    public StoreDataLocal(SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
    }

    @Override
    public Observable<Store> getOurCompanyAsync() {
        return (new RealmApi()).getAsync(new BiConsumer<ObservableEmitter<? super Store>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Store> observableEmitter, Realm realm) throws Exception {
                // we will need edit on future
                Store company = realm.where(Store.class).findFirst();
                observableEmitter.onNext(company);
            }
        });
    }

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
    public void saveStoreId(long storeId) {
        mPrefsApi.put(SharedPrefsKey.STORE_ID, storeId);
    }

    @Override
    public String getOurStoreIdSync() {
//        return RealmApi.getSync().where(Store.class)
//                .findFirst().getId();
        return "";
    }
}
