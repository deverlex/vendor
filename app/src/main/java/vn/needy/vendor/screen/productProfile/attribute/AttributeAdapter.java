package vn.needy.vendor.screen.productProfile.attribute;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import vn.needy.vendor.R;
import vn.needy.vendor.model.wrapper.AttributeWrapper;
import vn.needy.vendor.databinding.ItemAddAttrsBinding;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 04/12/2017.
 */

public class AttributeAdapter extends BaseRecyclerViewAdapter<AttributeAdapter.ItemViewHolder> {

    private static final String TAG = AttributeAdapter.class.getName();

    private final List<AttributeWrapper> mAttributes;
    private final Set<Integer> mIds;

    public AttributeAdapter(@NonNull Context context, List<AttributeWrapper> attributes) {
        super(context);
        mAttributes = attributes;
        mIds = new HashSet<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAddAttrsBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_choose_attribute, parent, false);
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

    public void updateData(List<AttributeWrapper> attributes) {
        if (attributes == null) {
            return;
        }
        Log.w(TAG, "size of attributes????? " + attributes.size());
        mAttributes.addAll(attributes);
        notifyDataSetChanged();
    }

    public List<AttributeWrapper> getAttributes() {
        return mAttributes;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemAddAttrsBinding mBinding;

        @SuppressLint("ResourceType")
        public ItemViewHolder(ItemAddAttrsBinding binding) {
            super(binding.getRoot());
            // set ID for edit_text in each item
            LinearLayout layout = (LinearLayout) binding.getRoot();
            int id = (new Random()).nextInt(1000) + 10;
            layout.getChildAt(1).setId(id);

            mBinding = binding;
        }

        void bind(AttributeWrapper attribute) {
            mBinding.setViewModel(new ItemAttributeViewModel(attribute));
            mBinding.executePendingBindings();
        }
    }
}
