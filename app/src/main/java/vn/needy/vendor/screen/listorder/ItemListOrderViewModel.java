package vn.needy.vendor.screen.listorder;

import android.databinding.BaseObservable;

import vn.needy.vendor.model.Order;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 18/12/2017.
 */

public class ItemListOrderViewModel extends BaseObservable {

    private Order mOrder;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    public ItemListOrderViewModel(Order mOrder, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener) {
        this.mOrder = mOrder;
        this.mItemClickListener = mItemClickListener;
    }

    public String getDescribe() {
        if (mOrder.getStatus() == 0) {
            return "Đang chờ xác nhận";
        } else {
            return "Đang giao hàng";
        }
    }

    public String getTime() {
        if (mOrder.getStatus() == 0) {
            return String.format("Từ %s đến %s", mOrder.getReceiveFrom(), mOrder.getReceiveTo());
        } else {
            return String.format("Từ %s đến %s", mOrder.getTransportFrom(), mOrder.getTransportFrom());
        }
    }

    public void onItemClick() {
        if (mItemClickListener != null) {
            mItemClickListener.onItemRecyclerViewClick(mOrder);
        }
    }
}
