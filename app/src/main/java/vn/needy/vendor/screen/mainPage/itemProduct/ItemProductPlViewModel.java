package vn.needy.vendor.screen.mainPage.itemProduct;

import android.databinding.BaseObservable;

import vn.needy.vendor.port.wrapper.ProductPlWrapper;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ItemProductPlViewModel extends BaseObservable {

    private final ProductPlWrapper mProductPn;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemProductPlViewModel(ProductPlWrapper productPn, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
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
