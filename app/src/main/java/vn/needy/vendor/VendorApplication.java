package vn.needy.vendor;

import android.app.Application;

import vn.needy.vendor.datasource.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.service.VendorServiceClient;

public class VendorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsImpl.initialize(this);
        VendorServiceClient.initialize(this);
    }
}
