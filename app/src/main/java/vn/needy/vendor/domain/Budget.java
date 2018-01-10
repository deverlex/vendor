package vn.needy.vendor.domain;

import io.realm.RealmObject;

/**
 * Created by minh_dai on 06/01/2018.
 */

public class Budget extends RealmObject {

    private float amount;
    private float hold;

    public Budget() {
        super();
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getHold() {
        return hold;
    }

    public void setHold(float hold) {
        this.hold = hold;
    }
}
