package vn.needy.vendor.datasource;

import android.support.annotation.IntDef;

/**
 * Created by lion on 09/12/2017.
 */

@IntDef({ResponseCode.OK, ResponseCode.ERROR, ResponseCode.NO_CONTENT,
ResponseCode.UNAUTHORIZED, ResponseCode.NOT_IMPLEMENTED
})
public @interface ResponseCode {
    int OK = 200;
    int ERROR = 400;
    int NO_CONTENT = 204;
    int UNAUTHORIZED = 401;
    int NOT_IMPLEMENTED = 501;
}
