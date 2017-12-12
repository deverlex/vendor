package vn.needy.vendor.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lion on 12/12/2017.
 */

public class Store extends RealmObject {

    @PrimaryKey
    private String mId;
}
