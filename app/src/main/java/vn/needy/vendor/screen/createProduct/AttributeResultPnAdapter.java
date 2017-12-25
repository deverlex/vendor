package vn.needy.vendor.screen.createProduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemAttributeResultPnBinding;
import vn.needy.vendor.model.wrapper.AttributeWrapper;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 04/12/2017.
 */

public class AttributeResultPnAdapter extends BaseRecyclerViewAdapter<AttributeResultPnAdapter.ItemViewHolder> {

    private static final String TAG = AttributeResultPnAdapter.class.getName();

    private final List<AttributeWrapper> mAttributes;
    private final Set<Integer> mIds;

    public AttributeResultPnAdapter(@NonNull Context context, List<AttributeWrapper> attributes) {
        super(context);
        mAttributes = attributes;
        mIds = new HashSet<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemAttributeResultPnBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_attribute_result_pn, parent, false);
        return new AttributeResultPnAdapter.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mAttributes.get(position));
    }
    @Override
    public int getItemCount() {
        return mAttributes.size();
    }

    public void updateData(List<AttributeWrapper> attributes) {
        if (attributes == null) {
            return;
        }
        mAttributes.clear();
        mAttributes.addAll(attributes);
        notifyDataSetChanged();
    }

    public List<AttributeWrapper> getAttributes() {
        return mAttributes;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemAttributeResultPnBinding mBinding;

        @SuppressLint("ResourceType")
        public ItemViewHolder(ItemAttributeResultPnBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(AttributeWrapper attribute) {
            mBinding.setViewModel(new ItemAttributeResultPnViewModel(attribute));
            mBinding.executePendingBindings();
        }
    }
}
