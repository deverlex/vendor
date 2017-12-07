package vn.needy.vendor.screen.addProduct.addAttribute;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Attribute;
import vn.needy.vendor.databinding.ItemAddAttrsBinding;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 04/12/2017.
 */

public class AttributeAdapter extends BaseRecyclerViewAdapter<AttributeAdapter.ItemViewHolder> {

    private static final String TAG = AttributeAdapter.class.getName();

    private final List<Attribute> mAttributes;

    public AttributeAdapter(@NonNull Context context, List<Attribute> attributes) {
        super(context);
        mAttributes = attributes;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.w(TAG, "onCreateViewHolder");
        ItemAddAttrsBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_add_attrs, parent, false);
        return new AttributeAdapter.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mAttributes.get(position));
    }
    @Override
    public int getItemCount() {
        return mAttributes.size();
    }

    public void updateData(List<Attribute> attributes) {
        if (attributes == null) {
            return;
        }
        Log.w(TAG, "size of attributes????? " + attributes.size());
        mAttributes.addAll(attributes);
        notifyDataSetChanged();
    }

    public List<Attribute> getAttributes() {
        return mAttributes;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemAddAttrsBinding mBinding;

        public ItemViewHolder(ItemAddAttrsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Attribute attribute) {
            mBinding.setViewModel(new ItemAttributeViewModel(attribute));
            mBinding.executePendingBindings();
        }
    }
}
