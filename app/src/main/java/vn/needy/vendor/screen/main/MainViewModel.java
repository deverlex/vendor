package vn.needy.vendor.screen.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.BR;

/**
 * Created by truongpq on 10/01/2018.
 */

public class MainViewModel extends BaseObservable implements MainContract.ViewModel {
    private Context mContext;
    private MainContract.Presenter mPresenter;

    private int mNotificationCount;

    public MainViewModel(Context context) {
        this.mContext = context;
    }

    @Override
    public void onStart() {
        mPresenter.getCountNotificationsNotView();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public int getNotificationCount() {
        return mNotificationCount;
    }

    @Override
    public void onChangeNotification(int count) {
        mNotificationCount = count;
        notifyPropertyChanged(BR.notificationCount);
    }

    @Override
    public void onResume() {
        mPresenter.getCountNotificationsNotView();
    }
}
