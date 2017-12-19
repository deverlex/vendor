package vn.needy.vendor.screen.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import java.util.List;

import vn.needy.vendor.model.Notification;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 21/10/2017.
 */

public class NotificationViewModel extends BaseObservable implements NotificationConstract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private NotificationConstract.Presenter mPresenter;

    private Context mContext;
    private NotificationAdapter mAdapter;

    public NotificationViewModel(Context context, NotificationAdapter adapter) {
        this.mContext = context;
        this.mAdapter = adapter;
        adapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onGetNotification();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(NotificationConstract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public NotificationAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onSetNotifications(List<Notification> notifications) {
        mAdapter.setData(notifications);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        Intent intent=new Intent();
        intent.setComponent(new ComponentName(mContext.getPackageName(), mContext.getPackageName() + ((Notification)item).getReferenceGUI()));
        mContext.startActivity(intent);
    }
}
