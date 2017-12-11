package vn.needy.vendor.repository.local;

import android.util.Log;

import io.realm.Realm;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.repository.CompanyData;

/**
 * Created by lion on 10/12/2017.
 */

public class CompanyDataLocal implements CompanyData.Local {

    private static final String TAG = CompanyDataLocal.class.getName();

    @Override
    public void saveCompanySync(final Company company) {
        RealmApi.getSync().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(company);
                Log.w(TAG, "INSERT COMPANY SUCCESS.................OOOOOT");
            }
        });
    }

    @Override
    public String getCompanyIdSync() {
        return RealmApi.getSync().where(Company.class)
                .findFirst().getId();
    }
}
