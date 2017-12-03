package vn.needy.vendor.api.v1.company;

import io.reactivex.Observable;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;

/**
 * Created by lion on 07/10/2017.
 */

public interface CompanyDataSource {
    interface LocalDataSource {
        Observable<Void> saveCompany(Company company);

        Observable<Company> getCompany();
    }

    interface RemoteDataSource {

        Observable<CompanyResponse> registerCompany(RegisterCompanyRequest registerCompanyRequest);

        Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken);

        Observable<CompanyResponse> getCompanyInformation();
    }
}