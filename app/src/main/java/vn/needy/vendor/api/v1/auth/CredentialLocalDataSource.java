package vn.needy.vendor.api.v1.auth;

import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 14/10/2017.
 */

public class CredentialLocalDataSource implements CredentialDataSource.LocalDataSource {

    private SharedPrefsApi mPrefsApi;

    public CredentialLocalDataSource(SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
    }

    @Override
    public void saveToken(String token) {
        mPrefsApi.put(SharedPrefsKey.TOKEN_KEY, token);
    }

    @Override
    public void clear() {
        mPrefsApi.clear();
    }
}
