package vn.needy.vendor.screen.mainPage.itemProduct;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Locale;

import vn.needy.vendor.port.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ItemProductPnViewModel extends BaseObservable {

    private final ProductPnWrapper mProductPn;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemProductPnViewModel(ProductPnWrapper productPn, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
        this.mProductPn = productPn;
        this.mItemClickListener = itemClickListener;
    }

    @Bindable
    public String getName() {
        return mProductPn.getName();
    }

    @Bindable
    public String getPrice() {
        return String.format(Locale.getDefault(), "đ %f", mProductPn.getPrice());
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mProductPn);
    }
}
