package vn.needy.vendor.datasource.company;

import io.reactivex.Observable;
import vn.needy.vendor.datasource.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.datasource.company.request.RegisterCompanyRequest;
import vn.needy.vendor.datasource.BaseResponse;
import vn.needy.vendor.datasource.company.response.CompanyInfoResponse;

/**
 * Created by lion on 07/10/2017.
 */

public interface CompanyDataSource {
    Observable<CompanyInfoResponse> getCompanyInformation();

    Observable<CompanyInfoResponse> registerCompany(RegisterCompanyRequest request);

    Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken);

    Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest);

    void saveCompanyId(String companyId);
}
