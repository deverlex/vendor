package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResp;
import vn.needy.vendor.repository.remote.user.response.CompanyResp;

/**
 * Created by lion on 10/12/2017.
 */

public interface CompanyData {

    interface Remote {
        Observable<CompanyResp> findOurCompany();

        Observable<CompanyInfoResp> getCompanyInformation(String companyId);

        Observable<CompanyInfoResp> registerCompany(RegisterCompanyRequest request);

        Observable<BaseResponse> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest);

        Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken);
    }

    interface Local {

        Observable<Company> getOurCompanyAsync();

        void saveCompanySync(Company company);

        String getOurCompanyIdSync();
    }
}
