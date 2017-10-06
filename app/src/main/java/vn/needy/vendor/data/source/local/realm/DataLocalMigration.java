package vn.needy.vendor.data.source.local.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by lion on 05/10/2017.
 */

public class DataLocalMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        // Migrate from version 0 to version 1
        if (oldVersion == 0) {
            // do migration
            oldVersion++;
        }

        // Migrate from version 1 to version 2
        if (oldVersion == 1) {
            // do migration
            oldVersion++;
        }
    }
}
