package vn.needy.vendor.screen.category;

import android.databinding.BaseObservable;

import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 03/11/2017.
 */

public class ItemCategoryViewModel extends BaseObservable {
    private final Category mCategory;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemCategoryViewModel(Category category,
                                 BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mCategory = category;
        mItemClickListener = listener;
    }

    public String getTitleCategory() {
        if (mCategory != null) {
            return mCategory.getTitle();
        }
        return "";
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mCategory);
    }
}
