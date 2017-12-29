package vn.needy.vendor.screen.listorder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemOrderBinding;
import vn.needy.vendor.model.Order;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 18/12/2017.
 */

public class ListOrderAdapter extends BaseRecyclerViewAdapter<ListOrderAdapter.ItemViewHolder> {


    private List<Order> mOrders;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected ListOrderAdapter(@NonNull Context context, List<Order> orders) {
        super(context);
        mOrders = orders;
    }

    @Override
    public ListOrderAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemOrderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_order, parent, false);
        return new ListOrderAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ListOrderAdapter.ItemViewHolder holder, int position) {
        holder.bind(mOrders.get(position));
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public void setData(List<Order> orders) {
        mOrders.clear();
        mOrders.addAll(orders);
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemOrderBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

        public ItemViewHolder(ItemOrderBinding binding, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            mItemClickListener = itemClickListener;
        }

        void bind(Order order) {
            mBinding.setViewModel(new ItemListOrderViewModel(order, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
