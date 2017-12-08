package vn.needy.vendor.api.v1.company;

import vn.needy.vendor.database.sharedprf.SharedPrefsApi;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyLocalDataSource implements CompanyDataSource.LocalDataSource {

    private final static String TAG = CompanyLocalDataSource.class.getName();

    private SharedPrefsApi mPrefsApi;

    public CompanyLocalDataSource(SharedPrefsApi prefsApi) {
        mPrefsApi = prefsApi;
    }

}
