package vn.needy.vendor.port;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.needy.vendor.BuildConfig;
import vn.needy.vendor.port.adapter.BooleanAdapter;
import vn.needy.vendor.port.adapter.IntegerAdapter;
import vn.needy.vendor.port.middleware.RxErrorHandlingCallAdapterFactory;

/**
 * Created by lion on 23/09/2017.
 */

public class ServiceClient {

    private static final String TAG = ServiceClient.class.getName();

    private static final int CONNECTION_TIMEOUT = 5;

    public static <T> T createService(Application application, String endPoint, Class<T> serviceClass) {
        return createService(application, endPoint, serviceClass, getGsonConfig(), null);
    }

    public static <T> T createService(Application application, String endPoint, Class<T> serviceClass,
                               Interceptor interceptor) {
        return createService(application, endPoint, serviceClass, getGsonConfig(), interceptor);
    }

    private static <T> T createService(Application application, String endPoint,
                                       Class<T> serviceClass, @NonNull Gson gson, Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        int cacheSize = 30 * 1024 * 1024; // 10 MiB
        httpClientBuilder.cache(new Cache(application.getCacheDir(), cacheSize));
        if (interceptor != null) {
            ///clear all interceptors before add new
            httpClientBuilder.networkInterceptors().clear();
            httpClientBuilder.addInterceptor(interceptor);
        }
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(endPoint)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            httpClientBuilder.addInterceptor(logging);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Retrofit retrofit = builder.client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    static Gson getGsonConfig() {
        BooleanAdapter booleanAdapter = new BooleanAdapter();
        IntegerAdapter integerAdapter = new IntegerAdapter();
        return new GsonBuilder().registerTypeAdapter(Boolean.class, booleanAdapter)
                .registerTypeAdapter(boolean.class, booleanAdapter)
                .registerTypeAdapter(Integer.class, integerAdapter)
                .registerTypeAdapter(int.class, integerAdapter)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
