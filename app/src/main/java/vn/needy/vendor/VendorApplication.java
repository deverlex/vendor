package vn.needy.vendor;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import vn.needy.vendor.database.realm.DataLocalMigration;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.port.service.VendorServiceClient;

public class VendorApplication extends Application {
    private static final String REALM_SCHEMA_NAME = "vendor.realm";
    private static final int REALM_SCHEMA_VERSION = 0;

    private Activity activeActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsImpl.initialize(this);
        VendorServiceClient.initialize(this);
        // migrate realm
        initAndMigrateRealmIfNeeded();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                activeActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                activeActivity = null;
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private void initAndMigrateRealmIfNeeded() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name(REALM_SCHEMA_NAME)
                .schemaVersion(REALM_SCHEMA_VERSION)
                .migration(new DataLocalMigration())
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance(); // Automatically run migration if needed
        realm.close();
    }

    public Activity getActiveActivity(){
        return activeActivity;
    }
}
