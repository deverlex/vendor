package vn.needy.vendor.repository.local;

import android.os.AsyncTask;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.model.Company;
import vn.needy.vendor.repository.CompanyData;

/**
 * Created by lion on 10/12/2017.
 */

public class CompanyDataLocal implements CompanyData.Local {

    @Override
    public void saveCompanySync(final Company company) {
        RealmApi.getInstance().getSync().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(company);
            }
        });
    }

    @Override
    public String getCompanyIdSync() {
        return RealmApi.getInstance().getSync().where(Company.class)
                .findFirst().getId();
    }
}
