package vn.needy.vendor.repository.local;

import android.util.Log;

import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.domain.User;
import vn.needy.vendor.repository.UserData;
import vn.needy.vendor.database.realm.RealmApi;

/**
 * Created by lion on 10/12/2017.
 */

public class UserDataLocal implements UserData.Local {

    private static final String TAG = UserDataLocal.class.getName();

    private SharedPrefsApi mPrefsApi;

    public UserDataLocal(SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
    }

    @Override
    public void saveUserSync(final User user) {
        RealmApi.getSync().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(user);
                Log.w(TAG, "saveUserSync....................DONE");
            }
        });
    }

    @Override
    public void saveAccessTokenSync(String token) {
        mPrefsApi.put(SharedPrefsKey.ACCESS_TOKEN, token);
    }

    @Override
    public void saveRefreshTokenSync(String token) {
        mPrefsApi.put(SharedPrefsKey.REFRESH_TOKEN, token);
    }

    @Override
    public void saveExpiresIn(long expiresIn) {
        mPrefsApi.put(SharedPrefsKey.EXPIRES_TOKEN_IN, expiresIn + System.currentTimeMillis() / 1000);
    }

    @Override
    public void clearToken() {
        mPrefsApi.remove(SharedPrefsKey.ACCESS_TOKEN);
    }

    @Override
    public void clearAll() {
        mPrefsApi.clear();
        (new RealmApi()).transactionAsync(new BiConsumer<ObservableEmitter<? super Object>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Object> observableEmitter, Realm realm) throws Exception {
                realm.deleteAll();
            }
        });
    }
}
