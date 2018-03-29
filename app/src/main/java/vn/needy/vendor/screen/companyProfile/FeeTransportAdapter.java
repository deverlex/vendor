package vn.needy.vendor.screen.companyProfile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.realm.RealmList;
import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemFeeTransportBinding;
import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 13/12/2017.
 */

public class FeeTransportAdapter extends BaseRecyclerViewAdapter<FeeTransportAdapter.ItemViewHolder> {

    private RealmList<FeeTransport> mFeeTransport;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    protected FeeTransportAdapter(@NonNull Context context, RealmList<FeeTransport> feeTransport) {
        super(context);
        this.mFeeTransport = feeTransport;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFeeTransportBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_fee_transport, parent, false);
        return new FeeTransportAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mFeeTransport.get(position));
    }

    @Override
    public int getItemCount() {
        return mFeeTransport.size();
    }

    public void updateData(RealmList<FeeTransport> feeTransports) {
        if (feeTransports == null) {
            return;
        }
        mFeeTransport.clear();
        mFeeTransport.addAll(feeTransports);
        notifyDataSetChanged();
    }

    public void setData(RealmList<FeeTransport> feeTransports) {
        if (feeTransports == null) {
            return;
        }
        mFeeTransport = feeTransports;
        notifyDataSetChanged();
    }

    public void addItem() {
        FeeTransport feeTransport = new FeeTransport();
        mFeeTransport.add(feeTransport);
        notifyItemInserted(mFeeTransport.indexOf(feeTransport));
    }

    public void removeItem(FeeTransport feeTransport) {
        int position = mFeeTransport.indexOf(feeTransport);
        mFeeTransport.remove(position);
        notifyItemRemoved(position);
    }

    public List<FeeTransport> getData() {
        return mFeeTransport;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemFeeTransportBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        public ItemViewHolder(ItemFeeTransportBinding mBinding, OnRecyclerViewItemClickListener<Object> mItemClickListener) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
            this.mItemClickListener = mItemClickListener;
        }

        void bind(FeeTransport feeTransport) {
            mBinding.setViewModel(new ItemFeeTransportViewModel(feeTransport, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
