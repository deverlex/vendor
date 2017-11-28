package vn.needy.vendor.api.v1.auth;

import com.google.gson.Gson;

import vn.needy.vendor.database.model.Credential;
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
    public void saveCredential(Credential credential) {
        String cred = new Gson().toJson(credential);
        mPrefsApi.put(SharedPrefsKey.KEY_CREDENTIAL, cred);
    }

    @Override
    public Credential getCredential() {
        String data = mPrefsApi.get(SharedPrefsKey.KEY_CREDENTIAL, String.class);
        return new Gson().fromJson(data, Credential.class);
    }

    @Override
    public void clear() {
        mPrefsApi.clear();
    }
}
