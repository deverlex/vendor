package vn.needy.vendor.screen;

/**
 * Created by lion on 23/09/2017.
 */

/**
 * BaseView
 */
public interface BaseViewModel<T extends BasePresenter> {

    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
