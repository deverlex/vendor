package vn.needy.vendor.data.model;

import android.support.annotation.StringDef;

@StringDef({GenderType.MALE, GenderType.FEMALE})
public @interface GenderType {
    String MALE = "male";
    String FEMALE = "female";
}
