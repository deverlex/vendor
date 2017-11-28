package vn.needy.vendor.api.v1.user;

import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;
import vn.needy.vendor.api.base.BaseLocalDataSource;
import vn.needy.vendor.database.model.User;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 04/10/2017.
 */

public class UserLocalDataSource extends BaseLocalDataSource implements UserDataSource.LocalDataSource {

    private static final String TAG = UserLocalDataSource.class.getName();

    private SharedPrefsApi mPrefsApi;

    public UserLocalDataSource(RealmApi realmApi, SharedPrefsApi prefsApi) {
        super(realmApi);
        mPrefsApi = prefsApi;
    }

    @Override
    public void saveUser(final User user) {
        mRealmApi.realmTransactionAsync(new BiConsumer<ObservableEmitter<? super Object>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Object> observableEmitter, Realm realm) throws Exception {
                try {
                    realm.insertOrUpdate(user);
                } catch (IllegalStateException e) {
                    observableEmitter.tryOnError(e);
                }
            }
        });
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void clearData() {

    }

    @Override
    public void saveToken(String token) {
        mPrefsApi.put(SharedPrefsKey.TOKEN_KEY, token);
    }
}
