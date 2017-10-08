package vn.needy.vendor.data.source;

import io.reactivex.Observable;
import vn.needy.vendor.data.model.Company;

/**
 * Created by lion on 07/10/2017.
 */

public interface CompanyDataSource {
    interface LocalDataSource {
        void saveCompany(Company company);
    }

    interface RemoteDataSource {
        Observable<Company> findCompany();
    }
}
