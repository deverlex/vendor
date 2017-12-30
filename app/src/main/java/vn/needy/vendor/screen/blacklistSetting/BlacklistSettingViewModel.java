package vn.needy.vendor.screen.blacklistSetting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.model.BlockUser;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 25/12/2017.
 */

public class BlacklistSettingViewModel implements BlacklistSettingContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private Context mContext;
    private BlacklistSettingContract.Presenter mPresenter;
    private BlackListSettingAdapter mBlacklistSettingAdapter;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemListener;


    public BlacklistSettingViewModel(Context mContext, BlackListSettingAdapter mBlacklistSettingAdapter) {
        this.mContext = mContext;
        this.mBlacklistSettingAdapter = mBlacklistSettingAdapter;
        mBlacklistSettingAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.getUserList();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(BlacklistSettingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ((Activity)mContext).onBackPressed();
    }

    @Override
    public void setListBlockUser(List<BlockUser> listBlockUser) {
        mBlacklistSettingAdapter.updateData(listBlockUser);
    }


    @Override
    public void updateListBlockUser(List<BlockUser> listBlockUser) {
        mBlacklistSettingAdapter.updateData(listBlockUser);
    }

    @Override
    public void onItemClicked() {
        if (mItemListener != null)
            return;
        mItemListener.onItemRecyclerViewClick(mBlacklistSettingAdapter);
    }


    public int getSizeBlackList()
    {
        return mBlacklistSettingAdapter.getItemCount();
    }

    public BlackListSettingAdapter getBlacklistSettingAdapter() {
        return mBlacklistSettingAdapter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        showDialog();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(mContext.getString(R.string.dialog_block));
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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
