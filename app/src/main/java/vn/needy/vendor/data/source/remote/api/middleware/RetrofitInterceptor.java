package vn.needy.vendor.data.source.remote.api.middleware;

import android.util.Log;

import okhttp3.Interceptor;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;
import vn.needy.vendor.data.model.Auth;

/**
 * Created by lion on 23/09/2017.
 */

public class RetrofitInterceptor implements Interceptor {

    private static final String TAG = RetrofitInterceptor.class.getName();

    private Auth mAuth;

    public RetrofitInterceptor(Auth auth) {
        mAuth = auth;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = initializeHeader(chain);
        Request request = builder.build();
        Response response = chain.proceed(request);
        return response;
    }

    private Request.Builder initializeHeader(Chain chain) {
        Request originRequest = chain.request();
        Request.Builder builder = originRequest.newBuilder()
                .header("Accept", "application/json;charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store");

        if (mAuth != null) {
            builder.header("X-User-Key", mAuth.getKey());
            builder.header("X-User-Token", mAuth.getToken());
        }

        return builder.method(originRequest.method(), originRequest.body());
    }
}
