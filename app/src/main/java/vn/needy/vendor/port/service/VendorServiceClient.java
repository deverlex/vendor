package vn.needy.vendor.port.service;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.port.ServiceClient;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.middleware.RetrofitInterceptor;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 04/10/2017.
 */

public class VendorServiceClient extends ServiceClient {

    private static VendorApi mVendorApi;

    public static VendorApi initialize(@NonNull Application application) {
        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
        String token = prefsApi.get(SharedPrefsKey.TOKEN_KEY, String.class);
        RetrofitInterceptor interceptor = null;
        if (!TextUtils.isEmpty(token)) {
            interceptor = new RetrofitInterceptor(token);
        }
        mVendorApi = createService(application, Constant.API_END_POINT_URL, VendorApi.class, interceptor);
        return mVendorApi;
    }

    public static VendorApi getInstance() {
        if (mVendorApi == null) {
            throw new RuntimeException("Need call method FOrderServiceClient#initialize() first");
        }
        return mVendorApi;
    }

}
