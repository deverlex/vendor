package vn.needy.vendor.screen.budget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemBudgetBinding;
import vn.needy.vendor.domain.Budget;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;


/**
 * Created by minh_dai on 02/01/2018.
 */

public class BudgetRecyclerViewAdapter extends BaseRecyclerViewAdapter<BudgetRecyclerViewAdapter.ItemViewHolder> {

    private List<BudgetActivity.Coin> mList;
    private Context mContext;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    public BudgetRecyclerViewAdapter(@NonNull Context context, List<BudgetActivity.Coin> mList) {
        super(context);
        this.mList = mList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBudgetBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_budget, parent, false);

        return new BudgetRecyclerViewAdapter.ItemViewHolder(binding , mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<BudgetActivity.Coin> list) {
        if (list == null) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public int getPosition(BudgetActivity.Coin list){
        return mList.indexOf(list);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;
        private ItemBudgetBinding mBinding;


        public ItemViewHolder(ItemBudgetBinding binding, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
            super(binding.getRoot());

            this.mBinding = binding;
            this.mItemClickListener = itemClickListener;
        }

        void bind(BudgetActivity.Coin cargo) {

            mBinding.setViewModel(new ItemBudgetRecyclerView(cargo, mItemClickListener));
            mBinding.executePendingBindings();
        }

    }
}
