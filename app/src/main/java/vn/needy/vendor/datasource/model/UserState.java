package vn.needy.vendor.datasource.model;

import android.support.annotation.IntDef;

@IntDef({UserState.DELETED,UserState.LOCKED, UserState.INACTIVE, UserState.ACTIVE})
public @interface UserState {
    int DELETED = -2;
    int LOCKED = -1;
    int INACTIVE = 0;
    int ACTIVE = 1;
}
