package vn.needy.vendor.data.source.remote;

import io.reactivex.Observable;
import retrofit2.Response;
import vn.needy.vendor.data.source.CredentialDataSource;
import vn.needy.vendor.data.source.remote.api.request.LoginRequest;
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
    public Observable<Response<Void>> login(String phoneNumber, String passWord) {
        return mVendorApi.login(new LoginRequest(phoneNumber, passWord));
    }
}
