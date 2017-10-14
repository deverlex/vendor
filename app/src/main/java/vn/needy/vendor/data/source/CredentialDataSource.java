package vn.needy.vendor.data.source;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.data.model.Credential;

/**
 * Created by lion on 13/10/2017.
 */

public interface CredentialDataSource {

    interface RemoteDataSource {
        Observable<Response<Void>> login(String phoneNumber, String passWord);
    }

    interface LocalDataSource {
        void saveToken(String token);

        void saveCredential(Credential credential);

        Credential getCredential();

        void clear();
    }
}
