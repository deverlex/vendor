package vn.needy.vendor.screen.addProduct;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.data.model.Image;
import vn.needy.vendor.databinding.ItemImageBinding;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.category.CategoryAdapter;
import vn.needy.vendor.utils.ViewUtil;

/**
 * Created by lion on 08/11/2017.
 */

public class ImageAdapter extends BaseRecyclerViewAdapter<ImageAdapter.ItemViewHolder> {

    private final List<Image> mImages;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected ImageAdapter(@NonNull Context context, List<Image> images) {
        super(context);
        mImages = images;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemImageBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_image, parent, false);
        return new ImageAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mImages.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<Image> images) {
        if (images == null) {
            return;
        }
        mImages.clear();
        mImages.addAll(images);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemImageBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        ItemViewHolder(ItemImageBinding binding,
                       BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Image image) {
            mBinding.setViewModel(new ItemImageViewModel(image, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }


}
