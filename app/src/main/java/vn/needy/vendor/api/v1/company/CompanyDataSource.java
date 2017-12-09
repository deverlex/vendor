package vn.needy.vendor.api.v1.company;

import io.reactivex.Observable;
import vn.needy.vendor.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;

/**
 * Created by lion on 07/10/2017.
 */

public interface CompanyDataSource {
    Observable<CompanyResponse> getCompanyInformation();

    Observable<CompanyResponse> registerCompany(RegisterCompanyRequest request);

    Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken);

    Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest);
}
