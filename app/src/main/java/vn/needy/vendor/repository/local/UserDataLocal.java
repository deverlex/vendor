package vn.needy.vendor.repository.local;

import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.repository.UserData;
import vn.needy.vendor.database.realm.RealmApi;

/**
 * Created by lion on 10/12/2017.
 */

public class UserDataLocal implements UserData.Local {

    private RealmApi mRealmApi;
    private SharedPrefsApi mPrefsApi;

    public UserDataLocal(RealmApi realmApi, SharedPrefsApi prefsApi) {
        mRealmApi = realmApi;
        mPrefsApi = prefsApi;
    }

    @Override
    public void saveToken(String token) {
        mPrefsApi.put(SharedPrefsKey.TOKEN_KEY, token);
    }

    @Override
    public void clearToken() {
        mPrefsApi.remove(SharedPrefsKey.TOKEN_KEY);
    }

    @Override
    public void clearAll() {
        mPrefsApi.clear();
        mRealmApi.transactionAsync(new BiConsumer<ObservableEmitter<? super Object>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Object> observableEmitter, Realm realm) throws Exception {
                realm.deleteAll();
            }
        });
    }
}
