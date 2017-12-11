package vn.needy.vendor.screen.category;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.databinding.ItemCategoryBinding;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoryAdapter extends BaseRecyclerViewAdapter<CategoryAdapter.ItemViewHolder> {

    private static final String TAG = CategoryAdapter.class.getName();

    private final List<CategoryWrapper> mCategories;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected CategoryAdapter(@NonNull Context context, List<CategoryWrapper> categories) {
        super(context);
        mCategories = categories;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_category, parent, false);
        return new CategoryAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<CategoryWrapper> categories) {
        if (categories == null) {
            return;
        }
        mCategories.clear();
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemCategoryBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        ItemViewHolder(ItemCategoryBinding binding,
                       BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(CategoryWrapper category) {
            mBinding.setViewModel(new ItemCategoryViewModel(category, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
