package vn.needy.vendor.screen.mainPage.itemProduct;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemProductPnBinding;
import vn.needy.vendor.port.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.createProduct.childProduct.ChildProductAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ProductPnAdapter extends BaseRecyclerViewAdapter<ProductPnAdapter.ItemViewHolder>{

    private List<ProductPnWrapper> mProductPns;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;

    public ProductPnAdapter(@NonNull Context context, List<ProductPnWrapper> productPns) {
        super(context);
        this.mProductPns = productPns;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductPnBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_product_pn, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
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

    public void setData(List<ProductPnWrapper> productPns) {
        mProductPns.clear();
        mProductPns.addAll(productPns);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductPnBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;


        public ItemViewHolder(ItemProductPnBinding binding,
                              BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mItemClickListener = itemClickListener;
        }

        void bind(ProductPnWrapper productPn) {
            mBinding.setViewModel(new ItemProductPnViewModel(productPn, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
