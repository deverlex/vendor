package vn.needy.vendor.screen.createProduct;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemChildProductPlBinding;
import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 26/12/2017.
 */

public class ChildProductPlAdapter extends BaseRecyclerViewAdapter<ChildProductPlAdapter.ItemViewHolder>
        implements ItemChildProductPlViewModel.OnRemoveItemClick{

    private List<ProductPn> mProductPns;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected ChildProductPlAdapter(@NonNull Context context, List<ProductPn> productPns) {
        super(context);
        this.mProductPns = productPns;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemChildProductPlBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_child_product_pl, parent, false);
        return new ChildProductPlAdapter.ItemViewHolder(binding, mItemClickListener, this);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mProductPns.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductPns.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setData(List<ProductPn> productPns) {
        mProductPns.clear();
        mProductPns.addAll(productPns);
        notifyDataSetChanged();
    }

    public List<ProductPn> getData() {
        return mProductPns;
    }

    @Override
    public void onRemoveItemClicked(ProductPn productPn) {
        int position = mProductPns.indexOf(productPn);
        mProductPns.remove(productPn);
        notifyItemRemoved(position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemChildProductPlBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;
        private final ItemChildProductPlViewModel.OnRemoveItemClick mOnRemoveItemClick;

        public ItemViewHolder(ItemChildProductPlBinding binding,
                              BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener,
                              ItemChildProductPlViewModel.OnRemoveItemClick onRemoveItemClick) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mItemClickListener = itemClickListener;
            this.mOnRemoveItemClick = onRemoveItemClick;
        }

        public void bind(ProductPn productPn) {
            mBinding.setViewModel(new ItemChildProductPlViewModel(productPn, mItemClickListener, mOnRemoveItemClick));
            mBinding.executePendingBindings();
        }
    }
}
