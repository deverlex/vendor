package vn.needy.vendor.screen.notification;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemNotificationBinding;
import vn.needy.vendor.domain.Notification;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 18/12/2017.
 */

public class NotificationAdapter extends BaseRecyclerViewAdapter<NotificationAdapter.ItemViewHolder> {

    private List<Notification> mNotifications;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected NotificationAdapter(@NonNull Context context, List<Notification> notifications) {
        super(context);
        this.mNotifications = notifications;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_notification, parent, false);
        return new NotificationAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mNotifications.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    public void setData(List<Notification> notifications) {
        mNotifications.clear();
        mNotifications.addAll(notifications);
        notifyDataSetChanged();
    }

    public List<Notification> getData() {
        return mNotifications;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public int getPosition(Notification notification) {
        return mNotifications.indexOf(notification);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemNotificationBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        public ItemViewHolder(ItemNotificationBinding binding,
                              BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mItemClickListener = itemClickListener;
        }

        void bind(Notification notification) {
            mBinding.setViewModel(new ItemNotificationViewModel(notification, mItemClickListener));
        }
    }
}
