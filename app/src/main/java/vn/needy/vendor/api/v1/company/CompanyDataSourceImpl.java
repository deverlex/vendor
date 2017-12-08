package vn.needy.vendor.api.v1.company;

import io.reactivex.Observable;
import vn.needy.vendor.api.base.BaseRepository;
import vn.needy.vendor.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyDataSourceImpl extends BaseRepository implements CompanyDataSource {

    public CompanyDataSourceImpl() {
        super();
    }

    public Observable<CompanyResponse> getCompanyInformation() {
        return mVendorApi.getCompanyInformation();
    }

    public Observable<CompanyResponse> registerCompany(RegisterCompanyRequest request) {
        return mVendorApi.registerCompany(request);
    }

    public Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken) {
        return mVendorApi.updateStaffFcmToken(companyId, fcmToken);
    }

    public Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest) {
        return mVendorApi.updateCompanyInformation(companyId, infoRequest);
    }
}
