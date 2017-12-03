package vn.needy.vendor.service;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.service.middleware.RetrofitInterceptor;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 04/10/2017.
 */

public class VendorServiceClient extends ServiceClient {

    private static VendorApi mVendorApiInstance;

    public static void initialize(@NonNull Application application) {
        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
        String token = prefsApi.get(SharedPrefsKey.TOKEN_KEY, String.class);
        RetrofitInterceptor interceptor = null;
        if (!TextUtils.isEmpty(token)) {
            interceptor = new RetrofitInterceptor(token);
        }
        mVendorApiInstance = createService(application, Constant.END_POINT_URL, VendorApi.class, interceptor);
    }

    public static VendorApi getInstance() {
        if (mVendorApiInstance == null) {
            throw new RuntimeException("Need call method FOrderServiceClient#initialize() first");
        }
        return mVendorApiInstance;
    }

}