package vn.needy.vendor.api.v1.auth;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;

/**
 * Created by lion on 13/10/2017.
 */

public interface AuthDataSource {

    Observable<CertificationResponse> login(String phoneNumber, String passWord);

    Observable<CertificationResponse> refresh(String token);

    Observable<Response<Void>> logout();

    void saveToken(String token);

    void clear();

}
