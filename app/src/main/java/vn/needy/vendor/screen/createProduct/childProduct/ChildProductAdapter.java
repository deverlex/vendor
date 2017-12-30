package vn.needy.vendor.screen.createProduct.childProduct;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemChooseChildProductBinding;
import vn.needy.vendor.model.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductAdapter extends BaseRecyclerViewAdapter<ChildProductAdapter.ItemViewHolder>
        implements ItemChildProductViewModel.OnCheckClickListener {
    private List<ProductPnWrapper> mProductPns;
    private List<ProductPnWrapper> mCheckedProductPns;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected ChildProductAdapter(@NonNull Context context, List<ProductPnWrapper> productPns, List<ProductPnWrapper> checkedProductPns) {
        super(context);
        this.mProductPns = productPns;
        this.mCheckedProductPns = checkedProductPns;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemChooseChildProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_choose_child_product, parent, false);
        return new ChildProductAdapter.ItemViewHolder(binding, mItemClickListener, this);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ProductPnWrapper productPn = mProductPns.get(position);
        holder.bind(productPn, contains(mCheckedProductPns, productPn));
    }

    @Override
    public int getItemCount() {
        return mProductPns.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setData(List<ProductPnWrapper> productPns) {
        mProductPns.clear();
        mProductPns.addAll(productPns);
        notifyDataSetChanged();
    }

    public List<ProductPnWrapper> getData() {
        return mProductPns;
    }

    public List<ProductPnWrapper> getCheckedProducts() {
        return mCheckedProductPns;
    }

    public boolean contains(List<ProductPnWrapper> productPns, ProductPnWrapper productPn) {
        for (ProductPnWrapper p : productPns) {
            if (p.getId() == productPn.getId()) return true;
        }
        return false;
    }


    @Override
    public void onCheckClicked(boolean isChecked, ProductPnWrapper productPn) {
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

        void bind(ProductPnWrapper productPn, boolean isChecked) {
            mBinding.setViewModel(new ItemChildProductViewModel(productPn, mItemClickListener, isChecked, mOnCheckClickListener));
            mBinding.executePendingBindings();
        }
    }
}
