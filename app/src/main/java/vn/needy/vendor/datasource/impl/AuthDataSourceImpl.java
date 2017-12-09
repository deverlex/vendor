package vn.needy.vendor.datasource.impl;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.api.base.BaseRepository;
import vn.needy.vendor.api.v1.auth.request.CredentialsRequest;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;
import vn.needy.vendor.datasource.AuthDataSource;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsApi;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 13/10/2017.
 */

public class AuthDataSourceImpl extends BaseRepository implements AuthDataSource {

    private SharedPrefsApi mPrefsApi;

    public AuthDataSourceImpl(SharedPrefsApi prefsApi) {
        super();
        mPrefsApi = prefsApi;
    }

    public Observable<CertificationResponse> login(String phoneNumber, String passWord) {
        return mVendorApi.login(new CredentialsRequest(phoneNumber, passWord));
    }

    @Override
    public Observable<CertificationResponse> refresh(String token) {
        return null;
    }

    @Override
    public Observable<Response<Void>> logout() {
        return null;
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
