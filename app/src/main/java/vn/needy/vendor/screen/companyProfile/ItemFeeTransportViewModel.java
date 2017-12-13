package vn.needy.vendor.screen.companyProfile;

import android.databinding.BaseObservable;

import vn.needy.vendor.model.FeeTransport;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 13/12/2017.
 */

public class ItemFeeTransportViewModel extends BaseObservable {
    private final FeeTransport mFeeTransport;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemFeeTransportViewModel(FeeTransport mFeeTransport, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener) {
        this.mFeeTransport = mFeeTransport;
        this.mItemClickListener = mItemClickListener;
    }

    public float getFrom() {
        return mFeeTransport.getFrom();
    }

    public float getTo() {
        return mFeeTransport.getTo();
    }

    public float getFee() {
        return mFeeTransport.getFee();
    }

    public void setFrom(float from) {
        if (mFeeTransport != null) {
            mFeeTransport.setFrom(from);
        }
    }

    public void setTo(float to) {
        if (mFeeTransport != null) {
            mFeeTransport.setTo(to);
        }
    }

    public void setFee(float fee) {
        if (mFeeTransport != null) {
            mFeeTransport.setFee(fee);
        }
    }

    public void onRemoveClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mFeeTransport);
    }
}
