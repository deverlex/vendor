package vn.needy.vendor.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import vn.needy.vendor.data.model.Auth;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsKey;
import vn.needy.vendor.data.source.remote.api.middleware.RetrofitInterceptor;
import vn.needy.vendor.utils.Constant;

/**
 * Created by lion on 04/10/2017.
 */

public class VendorServiceClient extends ServiceClient {

    private static VendorApi mVendorApiInstance;

    public static void initialize(@NonNull Application application) {
        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
        Auth auth = new Gson().fromJson(prefsApi.get(SharedPrefsKey.KEY_AUTH, String.class),
                Auth.class);

        RetrofitInterceptor interceptor = null;
        if (auth != null) {
            interceptor = new RetrofitInterceptor(auth);
        }
        mVendorApiInstance =
                createService(application, Constant.END_POINT_URL, VendorApi.class, interceptor);
    }

    public static VendorApi getInstance() {
        if (mVendorApiInstance == null) {
            throw new RuntimeException("Need call method FOrderServiceClient#initialize() first");
        }
        return mVendorApiInstance;
    }

}
