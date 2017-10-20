package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.remote.api.request.RegisterCompanyRequest;

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

    public Observable<Company> findCompanyInherent() {
        return mCompanyRemoteDataSource.findCompanyInherent();
    }

    public Observable<Company> registerCompany(RegisterCompanyRequest request) {
        return mCompanyRemoteDataSource.registerCompany(request);
    }

    public void saveCompany(Company company) {
        mCompanyLocalDataSource.saveCompany(company);
    }
}
