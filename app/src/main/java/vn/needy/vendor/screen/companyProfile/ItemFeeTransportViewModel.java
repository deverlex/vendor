package vn.needy.vendor.screen.companyProfile;

import android.databinding.BaseObservable;

import vn.needy.vendor.domain.FeeTransport;
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

    public String getFrom() {
        return String.valueOf(mFeeTransport.getFrom());
    }

    public String getTo() {
        return String.valueOf(mFeeTransport.getTo());
    }

    public String getFee() {
        return String.valueOf(mFeeTransport.getFee());
    }

    public void setFrom(String from) {
        if (mFeeTransport != null) {
            mFeeTransport.setFrom(Float.valueOf(from));
        }
    }

    public void setTo(String to) {
        if (mFeeTransport != null) {
            mFeeTransport.setTo(Float.valueOf(to));
        }
    }

    public void setFee(String fee) {
        if (mFeeTransport != null) {
            mFeeTransport.setFee(Float.valueOf(fee));
        }
    }

    public void onRemoveClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mFeeTransport);
    }
}
