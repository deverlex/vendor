package vn.needy.vendor.service.middleware;

import android.support.annotation.NonNull;

import okhttp3.Interceptor;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lion on 23/09/2017.
 */

public class RetrofitInterceptor implements Interceptor {

    private String mToken;

    public RetrofitInterceptor(@NonNull String token) {
        mToken = token;
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
        return originRequest.newBuilder()
                .header("Accept", "application/json;charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store")
                .header("Authorization", mToken)
                .method(originRequest.method(), originRequest.body());
    }
}
