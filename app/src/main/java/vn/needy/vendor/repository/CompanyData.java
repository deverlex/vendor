package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoReq;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResp;
import vn.needy.vendor.repository.remote.user.response.CompanyResp;

/**
 * Created by lion on 10/12/2017.
 */

public interface CompanyData {

    interface Remote {
        Observable<ResponseWrapper<CompanyResp>> findOurCompany();

        Observable<ResponseWrapper<CompanyInfoResp>> getCompanyInformation(String companyId);

        Observable<ResponseWrapper<CompanyInfoResp>> registerCompany(RegisterCompanyRequest request);

        Observable<ResponseWrapper> updateCompanyInformation(String companyId, UpdateCompanyInfoReq infoRequest);

        Observable<ResponseWrapper> updateStaffFcmToken(String companyId, String fcmToken);
    }

    interface Local {

        Observable<Company> getOurCompanyAsync();

        void saveCompanySync(Company company);

        String getOurCompanyIdSync();
    }
}
