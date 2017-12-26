package vn.needy.vendor.screen.createProduct.childProduct;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemChooseChildProductBinding;
import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductAdapter extends BaseRecyclerViewAdapter<ChildProductAdapter.ItemViewHolder>{
    private List<ProductPn> mProductPns;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected ChildProductAdapter(@NonNull Context context, List<ProductPn> productPns) {
        super(context);
        this.mProductPns = productPns;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemChooseChildProductBinding binding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_choose_child_product, parent, false);
        return new ChildProductAdapter.ItemViewHolder(binding, mItemClickListener);
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

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemChooseChildProductBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        public ItemViewHolder(ItemChooseChildProductBinding binding, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mItemClickListener = itemClickListener;
        }

        void bind(ProductPn productPn) {
            mBinding.setViewModel(new ItemChildProductViewModel(productPn, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
