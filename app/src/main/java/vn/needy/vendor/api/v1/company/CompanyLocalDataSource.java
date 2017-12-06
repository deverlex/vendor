package vn.needy.vendor.api.v1.company;

import android.os.AsyncTask;

import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyLocalDataSource implements CompanyDataSource.LocalDataSource {

    private final static String TAG = CompanyLocalDataSource.class.getName();

    private SharedPrefsApi mPrefsApi;

    public CompanyLocalDataSource(SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
    }

    @Override
    public void saveCompany(final Company company) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mPrefsApi.putObject(SharedPrefsKey.COMPANY, company);
            }
        });
    }

    @Override
    public Company getCompany() {
        return mPrefsApi.getObject(SharedPrefsKey.COMPANY, Company.class);
    }
}
