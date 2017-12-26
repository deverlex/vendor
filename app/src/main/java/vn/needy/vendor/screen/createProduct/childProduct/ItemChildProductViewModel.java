package vn.needy.vendor.screen.createProduct.childProduct;

import android.databinding.BaseObservable;

import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ItemChildProductViewModel extends BaseObservable {
    private final ProductPn mProductPn;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemChildProductViewModel(ProductPn productPn, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
        this.mProductPn = productPn;
        this.mItemClickListener = itemClickListener;
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mProductPn);
    }
}
