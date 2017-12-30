package vn.needy.vendor.screen.blacklistSetting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.R;
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

    public void onUnBlockClick()
    {
        showDialog();
    }

    @Bindable
    public BlockUser getBlockUser() {
        return mBlockUser;
    }

    public void onPersonalInfoBlockClick() {
        if (mItemclickListener == null)
            return;
        mItemclickListener.onItemRecyclerViewClick(mBlockUser);

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(mContext.getString(R.string.dialog_block));
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mBlackListSettingAdapter.removeItem(mBlockUser);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
