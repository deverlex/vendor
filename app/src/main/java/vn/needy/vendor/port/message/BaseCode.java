package vn.needy.vendor.port.message;

import android.support.annotation.IntDef;

/**
 * Created by lion on 09/12/2017.
 */

@IntDef({BaseCode.OK, BaseCode.BAD_REQUEST, BaseCode.NOT_FOUND,
BaseCode.UNAUTHORIZED, BaseCode.SERVER_ERROR
})
public @interface BaseCode {
    int OK = 200;
    int BAD_REQUEST = 400;
    int NOT_FOUND = 404;
    int CONFLICT = 409;
    int UNAUTHORIZED = 401;
    int SERVER_ERROR = 500;
}
