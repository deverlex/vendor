package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.Credential;
import vn.needy.vendor.data.source.remote.api.security.Certification;

/**
 * Created by lion on 13/10/2017.
 */

public interface CredentialDataSource {

    interface RemoteDataSource {
        Observable<Certification> login(String phoneNumber, String passWord);
    }

    interface LocalDataSource {
        void saveToken(String token);

        void saveCredential(Credential credential);

        Credential getCredential();

        void clear();
    }
}
