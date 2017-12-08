package vn.needy.vendor.api.v1.user;

import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 04/10/2017.
 */

public class UserLocalDataSource implements UserDataSource.LocalDataSource {

    private static final String TAG = UserLocalDataSource.class.getName();

    private SharedPrefsApi mPrefsApi;

    public UserLocalDataSource(SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
    }

    @Override
    public void clearData() {

    }

    @Override
    public void saveToken(String token) {
        mPrefsApi.put(SharedPrefsKey.TOKEN_KEY, token);
    }
}
