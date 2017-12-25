package vn.needy.vendor.port.message;

import android.support.annotation.StringDef;

/**
 * Created by lion on 14/12/2017.
 */

@StringDef({BaseStatus.OK, BaseStatus.ERROR})
public @interface BaseStatus {
    String OK = "ok";
    String ERROR = "error";
}
