package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.data.model.Credential;

/**
 * Created by lion on 13/10/2017.
 */

public class CredentialRepository {

    private CredentialDataSource.RemoteDataSource mCredentialRemoteDataSource;
    private CredentialDataSource.LocalDataSource mCredentialLocalDataSource;

    public CredentialRepository(CredentialDataSource.RemoteDataSource credentialRemoteDataSource,
                                CredentialDataSource.LocalDataSource credentialLocalDataSource) {
        mCredentialRemoteDataSource = credentialRemoteDataSource;
        mCredentialLocalDataSource = credentialLocalDataSource;
    }

    public Observable<Response<Void>> login(String phoneNumber, String passWord) {
        return mCredentialRemoteDataSource.login(phoneNumber, passWord);
    }

    public void saveToken(String token) {
        mCredentialLocalDataSource.saveToken(token);
    }

    public void saveCredential(Credential credential) {
        mCredentialLocalDataSource.saveCredential(credential);
    }

    public Credential getCredential() {
        return mCredentialLocalDataSource.getCredential();
    }

    public void clear() {
        mCredentialLocalDataSource.clear();
    }
}
