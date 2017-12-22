package vn.needy.vendor.repository.local;

import android.util.Log;

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

    private static final String TAG = CompanyDataLocal.class.getName();

    @Override
    public Observable<Company> getOurCompanyAsync() {
        return (new RealmApi()).getAsync(new BiConsumer<ObservableEmitter<? super Company>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Company> observableEmitter, Realm realm) throws Exception {
                // we will need edit on future
                Company company = realm.where(Company.class).findFirst();
                Log.w(TAG, "Get company is success???? " +  company.getName());
                observableEmitter.onNext(company);
            }
        });
    }

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
    public String getOurCompanyIdSync() {
        return RealmApi.getSync().where(Company.class)
                .findFirst().getId();
    }
}
