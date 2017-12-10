package vn.needy.vendor.datasource.authentication;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.datasource.BaseDataSource;
import vn.needy.vendor.datasource.authentication.request.LoginRequest;
import vn.needy.vendor.datasource.authentication.response.TokenResponse;
import vn.needy.vendor.service.sharedprf.SharedPrefsApi;
import vn.needy.vendor.service.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 13/10/2017.
 */

public class AuthenticationDataSourceImpl extends BaseDataSource implements AuthenticationDataSource {

    private SharedPrefsApi mPrefsApi;

    public AuthenticationDataSourceImpl(SharedPrefsApi prefsApi) {
        super();
        mPrefsApi = prefsApi;
    }

    public Observable<TokenResponse> login(String phoneNumber, String passWord) {
        return mVendorApi.login(new LoginRequest(phoneNumber, passWord));
    }

    @Override
    public Observable<TokenResponse> refresh(String token) {
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
