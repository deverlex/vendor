package vn.needy.vendor.api.v1.auth;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.api.base.BaseDataSource;
import vn.needy.vendor.api.v1.auth.request.CredentialsRequest;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;
import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 13/10/2017.
 */

public class CredentialRemoteDataSource extends BaseDataSource
        implements CredentialDataSource.RemoteDataSource {

    private static final String TAG = CredentialRemoteDataSource.class.getName();

    public CredentialRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
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
}
