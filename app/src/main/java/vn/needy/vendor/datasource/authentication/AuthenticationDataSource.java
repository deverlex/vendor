package vn.needy.vendor.datasource.authentication;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.datasource.authentication.response.TokenResponse;

/**
 * Created by lion on 13/10/2017.
 */

public interface AuthenticationDataSource {

    Observable<TokenResponse> login(String phoneNumber, String passWord);

    Observable<TokenResponse> refresh(String token);

    Observable<Response<Void>> logout();

    void saveToken(String token);

    void clear();

}
