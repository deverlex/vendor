package vn.needy.vendor.data.source.local;

import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.CompanyDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyLocalDataSource extends BaseLocalDataSource implements CompanyDataSource.LocalDataSource {

    public CompanyLocalDataSource(RealmApi realmApi) {
        super(realmApi);
    }

    @Override
    public void saveCompany(Company company) {

    }
}
