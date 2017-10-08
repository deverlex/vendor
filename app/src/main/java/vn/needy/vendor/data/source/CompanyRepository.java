package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.Company;

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

    public Observable<Company> findCompany() {
        return mCompanyRemoteDataSource.findCompany();
    }

    public void saveCompany(Company company) {

    }
}
