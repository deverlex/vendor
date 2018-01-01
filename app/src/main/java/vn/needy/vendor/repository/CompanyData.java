package vn.needy.vendor.repository;

import io.reactivex.Observable;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.domain.Company;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.company.request.UpdateCompanyInfoRequest;
import vn.needy.vendor.repository.remote.company.response.CompanyInfoResponse;
import vn.needy.vendor.repository.remote.user.response.CompanyResponse;

/**
 * Created by lion on 10/12/2017.
 */

public interface CompanyData {

    interface Remote {
        Observable<ResponseWrapper<CompanyResponse>> findOurCompany();

        Observable<ResponseWrapper<CompanyInfoResponse>> getCompanyInformation(String companyId);

        Observable<ResponseWrapper<CompanyInfoResponse>> registerCompany(RegisterCompanyRequest request);

        Observable<ResponseWrapper> updateCompanyInformation(String companyId, UpdateCompanyInfoRequest infoRequest);

        Observable<ResponseWrapper> updateStaffFcmToken(String companyId, String fcmToken);
    }

    interface Local {

        Observable<Company> getOurCompanyAsync();

        void saveCompanySync(Company company);

        String getOurCompanyIdSync();
    }
}
