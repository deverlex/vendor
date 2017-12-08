package vn.needy.vendor.api.v1.auth;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.database.model.Credential;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;

/**
 * Created by lion on 13/10/2017.
 */

public interface CredentialDataSource {

    interface RemoteDataSource {
        Observable<CertificationResponse> login(String phoneNumber, String passWord);

        Observable<CertificationResponse> refresh(String token);

        Observable<Response<Void>> logout();
    }

    interface LocalDataSource {
        void saveToken(String token);

        void clear();
    }
}
