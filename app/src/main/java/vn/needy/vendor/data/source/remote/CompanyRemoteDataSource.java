package vn.needy.vendor.data.source.remote;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.CompanyDataSource;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorApi;

public class CompanyRemoteDataSource extends BaseRemoteDataSource
        implements CompanyDataSource.RemoteDataSource {

    public CompanyRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<CompanyResponse> getCompanyInformation() {
        return mVendorApi.getCompanyInformation();
    }

    @Override
    public Observable<CompanyResponse> findCompanyInherent() {
        return mVendorApi.findCompanyInformation();
    }

    @Override
    public Observable<CompanyResponse> registerCompany(RegisterCompanyRequest registerCompanyRequest) {
        return mVendorApi.registerCompany(registerCompanyRequest);
    }

    @Override
    public Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken) {
        return mVendorApi.updateStaffFcmToken(companyId, fcmToken);
    }
}
