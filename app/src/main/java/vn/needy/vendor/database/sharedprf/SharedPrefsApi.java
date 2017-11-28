package vn.needy.vendor.database.sharedprf;

/**
 * Created by lion on 23/09/2017.
 */

public interface SharedPrefsApi {
    <T> T get(String key, Class<T> clazz);

    <T> void put(String key, T data);

    void clear();
}
