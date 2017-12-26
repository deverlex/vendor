package vn.needy.vendor.screen.createProduct;

import android.databinding.BaseObservable;

import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 26/12/2017.
 */

public class ItemChildProductPlViewModel extends BaseObservable{
    private final ProductPn mProductPn;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;
    private final OnRemoveItemClick mOnRemoveItemClick;

    public ItemChildProductPlViewModel(ProductPn productPn,
                                       BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener,
                                       OnRemoveItemClick onRemoveItemClick) {
        this.mProductPn = productPn;
        this.mItemClickListener = itemClickListener;
        this.mOnRemoveItemClick = onRemoveItemClick;
    }

    public void onRemoveItemClick() {
        if (mOnRemoveItemClick == null) {
            return;
        }
        mOnRemoveItemClick.onRemoveItemClicked(mProductPn);
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mProductPn);
    }

    interface OnRemoveItemClick {
        void onRemoveItemClicked(ProductPn productPn);
    }
}
