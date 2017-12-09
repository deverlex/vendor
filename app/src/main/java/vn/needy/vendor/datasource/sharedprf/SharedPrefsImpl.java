package vn.needy.vendor.datasource.sharedprf;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by lion on 23/09/2017.
 */

public class SharedPrefsImpl implements SharedPrefsApi {

    private static final String TAG = SharedPrefsImpl.class.getName();

    private static final String PREFS_NAME = "VendorSharedPreferences";

    private SharedPreferences mSharedPreferences;

    private static SharedPrefsImpl mPrefApi;

    private SharedPrefsImpl(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void initialize(Context context) {
        mPrefApi = new SharedPrefsImpl(context);
    }

    public static SharedPrefsApi getInstance() {
        return mPrefApi;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) mSharedPreferences.getString(key, "");
        } else if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(mSharedPreferences.getBoolean(key, false));
        } else if (clazz == Float.class) {
            return (T) Float.valueOf(mSharedPreferences.getFloat(key, 0));
        } else if (clazz == Integer.class) {
            return (T) Integer.valueOf(mSharedPreferences.getInt(key, 0));
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(mSharedPreferences.getLong(key, 0));
        }
        return null;
    }

    @Override
    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        }
        editor.apply();
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        String json = mSharedPreferences.getString(key, "");
        return new Gson().fromJson(json, clazz);
    }

    @Override
    public <T> void putObject(String key, T data) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String json = new Gson().toJson(data);
        editor.putString(key, json);
        editor.apply();
    }

    @Override
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}

