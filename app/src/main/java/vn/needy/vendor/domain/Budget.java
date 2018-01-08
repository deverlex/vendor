package vn.needy.vendor.domain;

import io.realm.RealmObject;

/**
 * Created by minh_dai on 06/01/2018.
 */

public class Budget extends RealmObject {

    private String amount;
    private String hold;

    public Budget() {
        super();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }
}
