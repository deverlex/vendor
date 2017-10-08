package vn.needy.vendor.data.model;

import android.support.annotation.IntDef;

@IntDef({UserState.REJECTED, UserState.INACTIVE, UserState.ACTIVE, UserState.LOCKED, UserState.BLOCKED})
public @interface UserState {
    int REJECTED = -2;
    int DELETED = -1;
    int INACTIVE = 0;
    int ACTIVE = 1;
    int LOCKED = 2;
    int BLOCKED = 3;
}
