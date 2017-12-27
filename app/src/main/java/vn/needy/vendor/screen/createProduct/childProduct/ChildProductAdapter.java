package vn.needy.vendor.screen.createProduct.childProduct;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemChooseChildProductBinding;
import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductAdapter extends BaseRecyclerViewAdapter<ChildProductAdapter.ItemViewHolder>
        implements ItemChildProductViewModel.OnCheckClickListener {
    private List<ProductPn> mProductPns;
    private List<ProductPn> mCheckedProductPns;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected ChildProductAdapter(@NonNull Context context, List<ProductPn> productPns, List<ProductPn> checkedProductPns) {
        super(context);
        this.mProductPns = productPns;
        mCheckedProductPns = checkedProductPns;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemChooseChildProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_choose_child_product, parent, false);
        return new ChildProductAdapter.ItemViewHolder(binding, mItemClickListener, this);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ProductPn productPn = mProductPns.get(position);
        holder.bind(productPn, contains(mCheckedProductPns, productPn));
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

    public List<ProductPn> getCheckedProducts() {
        return mCheckedProductPns;
    }

    public boolean contains(List<ProductPn> productPns, ProductPn productPn) {
        for (ProductPn p : productPns) {
            if (p.getId() == productPn.getId()) return true;
        }
        return false;
    }


    @Override
    public void onCheckClicked(boolean isChecked, ProductPn productPn) {
        if (isChecked) {
            mCheckedProductPns.add(productPn);
        } else {
            mCheckedProductPns.remove(productPn);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemChooseChildProductBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;
        private ItemChildProductViewModel.OnCheckClickListener mOnCheckClickListener;


        public ItemViewHolder(ItemChooseChildProductBinding binding,
                              BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener,
                              ItemChildProductViewModel.OnCheckClickListener onCheckClickListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mItemClickListener = itemClickListener;
            this.mOnCheckClickListener = onCheckClickListener;
        }

        void bind(ProductPn productPn, boolean isChecked) {
            mBinding.setViewModel(new ItemChildProductViewModel(productPn, mItemClickListener, isChecked, mOnCheckClickListener));
            mBinding.executePendingBindings();
        }
    }
}
