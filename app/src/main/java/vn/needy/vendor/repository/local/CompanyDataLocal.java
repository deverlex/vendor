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

    private RealmApi mRealmApi;

    public CompanyDataLocal(RealmApi realmApi) {
        mRealmApi = realmApi;
    }

    @Override
    public Observable<Void> saveCompany(final Company company) {
        return mRealmApi.transactionAsync(new BiConsumer<ObservableEmitter<? super Void>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Void> observableEmitter, Realm realm) throws Exception {
                realm.insertOrUpdate(company);
            }
        });
    }

    @Override
    public String getCompanyId() {
        return mRealmApi.getSync().where(Company.class)
                .findFirst().getId();
    }
}
