package vn.needy.vendor.screen.userProfile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemUserLocationBinding;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 17/01/2018.
 */

public class UserLocationAdapter extends BaseRecyclerViewAdapter<UserLocationAdapter.ItemViewHolder> {

    private List<UserLocationContext> mUserLocations;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected UserLocationAdapter(@NonNull Context context, List<UserLocationContext> userLocations) {
        super(context);
        mUserLocations = userLocations;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserLocationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_user_location, parent, false);
        return new UserLocationAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mUserLocations.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserLocations.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setData(List<UserLocationContext> userLocations) {
        if (userLocations != null) {
            mUserLocations.clear();
            mUserLocations.addAll(userLocations);
            notifyDataSetChanged();
        }
    }

    public void addLocation(UserLocationContext location) {
        mUserLocations.add(location);
        notifyItemInserted(mUserLocations.size());
    }

    public void editLocation(int position, UserLocationContext location) {
        mUserLocations.set(position, location);
        notifyItemChanged(position);
    }

    public int indexOf(UserLocationContext location) {
        return mUserLocations.indexOf(location);
    }

    public List<UserLocationContext> getLocations() {
        return mUserLocations;
    }

    public void removeLocation(int position) {
        mUserLocations.remove(position);
        notifyItemRemoved(position);
    }

    public UserLocationContext getLocation(int position) {
        return mUserLocations.get(position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemUserLocationBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        public ItemViewHolder(ItemUserLocationBinding binding, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = itemClickListener;
        }

        void bind(UserLocationContext userLocation) {
            mBinding.setViewModel(new ItemUserLocation(userLocation, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
