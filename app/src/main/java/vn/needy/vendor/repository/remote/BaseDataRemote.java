package vn.needy.vendor.repository.remote;

/**
 * Created by lion on 04/10/2017.
 */

public abstract class BaseDataRemote<T> {

    protected T mApi;

    public BaseDataRemote(T api) {
        mApi = api;
    }
}
