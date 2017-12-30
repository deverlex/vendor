package vn.needy.vendor.screen.createProduct;

import android.databinding.BaseObservable;

import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 13/12/2017.
 */

public class ItemFeeTransportPnViewModel extends BaseObservable {
    private final FeeTransport mFeeTransport;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemFeeTransportPnViewModel(FeeTransport mFeeTransport, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener) {
        this.mFeeTransport = mFeeTransport;
        this.mItemClickListener = mItemClickListener;
    }

    public String getFrom() {
        if (mFeeTransport.getFrom() == 0) {
            return "";
        } else {
            return String.valueOf(mFeeTransport.getFrom());
        }
    }

    public String getTo() {
        if (mFeeTransport.getTo() == 0) {
            return "";
        } else {
            return String.valueOf(mFeeTransport.getTo());
        }
    }

    public String getFee() {
        if (mFeeTransport.getFee() == 0) {
            return "";
        } else {
            return String.valueOf(mFeeTransport.getFee());
        }
    }

    public void setFrom(String from) {
        if (!from.isEmpty()) {
            mFeeTransport.setFrom(Float.valueOf(from));
        } else {
            mFeeTransport.setFrom(0);
        }
    }

    public void setTo(String to) {
        if (!to.isEmpty()) {
            mFeeTransport.setTo(Float.valueOf(to));
        } else {
            mFeeTransport.setTo(0);
        }
    }

    public void setFee(String fee) {
        if (!fee.isEmpty()) {
            mFeeTransport.setFee(Float.valueOf(fee));
        } else {
            mFeeTransport.setFee(0);
        }
    }

    public void onRemoveClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mFeeTransport);
    }
}
