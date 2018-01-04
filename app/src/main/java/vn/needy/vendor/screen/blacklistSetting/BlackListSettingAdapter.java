package vn.needy.vendor.screen.blacklistSetting;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemBlockedBinding;
import vn.needy.vendor.model.BlockUser;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by minh_dai on 29/12/2017.
 */

public class BlackListSettingAdapter extends BaseRecyclerViewAdapter<BlackListSettingAdapter.ItemViewHolder> {

    private int mPosition;
    private Context mContext;
    private List<BlockUser> mListUser;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    public BlackListSettingAdapter(@NonNull Context context, List<BlockUser> mListUser) {
        super(context);
        this.mContext = context;
        this.mListUser = mListUser;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBlockedBinding itemBlockedBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_blocked, parent , false);
        return new ItemViewHolder(mItemClickListener, itemBlockedBinding );
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mListUser.get(position));
    }

    @Override
    public int getItemCount() {
        return mListUser.size();
    }

    public void addItem(BlockUser user) {
        mListUser.add(user);
        notifyItemInserted(mListUser.indexOf(user));
    }

    public void removeItem(BlockUser user) {
        int position = mListUser.indexOf(user);
        mListUser.remove(position);
        notifyItemRemoved(position);
    }

    public List<BlockUser> getData() {
        return mListUser;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<BlockUser> users) {
        if (users == null) {
            return;
        }
        mListUser.clear();
        mListUser.addAll(users);
        notifyDataSetChanged();
    }

    public void setPosition(int position){
        this.mPosition = position;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemCLickListener;
        private  ItemBlockedBinding mItemBinding;

        public ItemViewHolder(OnRecyclerViewItemClickListener<Object> mItemCLickListener,
                              ItemBlockedBinding itemBlockedBinding) {

            super(itemBlockedBinding.getRoot());
            this.mItemCLickListener = mItemCLickListener;
            this.mItemBinding = itemBlockedBinding;
        }

        void bind(BlockUser user ) {
            mItemBinding.setViewModel(new ItemBlackListSettingViewModel(mContext, user,
                    mItemCLickListener, BlackListSettingAdapter.this ));
            mItemBinding.executePendingBindings();
        }
    }

}
