package vn.needy.vendor.data.source.remote;

import io.reactivex.Observable;
import vn.needy.vendor.data.source.CredentialDataSource;
import vn.needy.vendor.data.source.remote.api.request.CredentialsRequest;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorApi;

/**
 * Created by lion on 13/10/2017.
 */

public class CredentialRemoteDataSource extends BaseRemoteDataSource
        implements CredentialDataSource.RemoteDataSource {

    private static final String TAG = CredentialRemoteDataSource.class.getName();

    public CredentialRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<CertificationResponse> login(String phoneNumber, String passWord) {
        return mVendorApi.login(new CredentialsRequest(phoneNumber, passWord));
    }
}
