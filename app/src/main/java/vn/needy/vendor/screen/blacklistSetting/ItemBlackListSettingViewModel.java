package vn.needy.vendor.screen.blacklistSetting;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.model.BlockUser;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by minh_dai on 29/12/2017.
 */

public class ItemBlackListSettingViewModel extends BaseObservable{
    private Context mContext;
    private BlockUser mBlockUser;
    private BlackListSettingAdapter mBlackListSettingAdapter;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemclickListener;

    public ItemBlackListSettingViewModel(Context mContext, BlockUser mBlockUser, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemclickListener, BlackListSettingAdapter adapter ) {
        this.mContext = mContext;
        this.mBlockUser = mBlockUser;
        this.mItemclickListener = mItemclickListener;
        mBlackListSettingAdapter = adapter;
    }

    public void onDeleteBlockCLick()
    {
        mBlackListSettingAdapter.removeItem(mBlockUser);
    }

    @Bindable
    public BlockUser getBlockUser() {
        return mBlockUser;
    }

    public void onNextPageClick()
    {
        if (mItemclickListener == null)
            return;
        mItemclickListener.onItemRecyclerViewClick(mBlockUser);

    }
}
