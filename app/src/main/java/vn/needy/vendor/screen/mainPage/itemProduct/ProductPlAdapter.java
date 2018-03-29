package vn.needy.vendor.screen.mainPage.itemProduct;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemProductPlBinding;
import vn.needy.vendor.port.wrapper.ProductPlWrapper;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ProductPlAdapter extends BaseRecyclerViewAdapter<ProductPlAdapter.ItemViewHolder>{

    private List<ProductPlWrapper> mProductPls;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;

    public ProductPlAdapter(@NonNull Context context, List<ProductPlWrapper> productPls) {
        super(context);
        this.mProductPls = productPls;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductPlBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_product_pl, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mProductPls.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductPls.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setData(List<ProductPlWrapper> productPls) {
        mProductPls.clear();
        mProductPls.addAll(productPls);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductPlBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;


        public ItemViewHolder(ItemProductPlBinding binding,
                              BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mItemClickListener = itemClickListener;
        }

        void bind(ProductPlWrapper productPl) {
            mBinding.setViewModel(new ItemProductPlViewModel(productPl, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
