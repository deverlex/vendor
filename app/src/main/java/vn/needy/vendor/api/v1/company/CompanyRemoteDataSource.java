package vn.needy.vendor.api.v1.company;

import io.reactivex.Observable;
import vn.needy.vendor.api.base.BaseDataSource;
import vn.needy.vendor.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;
import vn.needy.vendor.service.VendorApi;

public class CompanyRemoteDataSource extends BaseDataSource
        implements CompanyDataSource.RemoteDataSource {

    public CompanyRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<CompanyResponse> getCompanyInformation() {
        return mVendorApi.getCompanyInformation();
    }

    @Override
    public Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest) {
        return mVendorApi.updateCompanyInformation(companyId, infoRequest);
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
