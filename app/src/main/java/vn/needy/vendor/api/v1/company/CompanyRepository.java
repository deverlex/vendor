package vn.needy.vendor.api.v1.company;

import io.reactivex.Observable;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyRepository {

    private CompanyDataSource.RemoteDataSource mCompanyRemoteDataSource;
    private CompanyDataSource.LocalDataSource mCompanyLocalDataSource;

    public CompanyRepository(CompanyDataSource.RemoteDataSource companyRemoteDataSource,
                             CompanyDataSource.LocalDataSource companyLocalDataSource) {
        mCompanyRemoteDataSource = companyRemoteDataSource;
        mCompanyLocalDataSource = companyLocalDataSource;
    }

    public Observable<CompanyResponse> getCompanyInformation() {
        return mCompanyRemoteDataSource.getCompanyInformation();
    }

    public Observable<CompanyResponse> registerCompany(RegisterCompanyRequest request) {
        return mCompanyRemoteDataSource.registerCompany(request);
    }

    public Observable<BaseResponse> updateStaffFcmToken(String companyId, String fcmToken) {
        return mCompanyRemoteDataSource.updateStaffFcmToken(companyId, fcmToken);
    }


    public void saveCompany(Company company) {
        mCompanyLocalDataSource.saveCompany(company);
    }

    public Observable<Company> getCompany() {
        return mCompanyLocalDataSource.getCompany();
    }
}