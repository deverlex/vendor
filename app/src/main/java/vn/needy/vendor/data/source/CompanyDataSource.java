package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.response.CompanyResponse;

/**
 * Created by lion on 07/10/2017.
 */

public interface CompanyDataSource {
    interface LocalDataSource {
        void saveCompany(Company company);
    }

    interface RemoteDataSource {
        Observable<Company> findCompanyInherent();

        Observable<Company> registerCompany(RegisterCompanyRequest registerCompanyRequest);

        Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken);

        Observable<CompanyResponse> getCompanyInformation();
    }
}
