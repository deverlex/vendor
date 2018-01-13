package vn.needy.vendor.screen.budget;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.domain.Budget;

/**
 * Created by minh_dai on 02/01/2018.
 */

public class ItemBudgetRecyclerView extends BaseObservable {

    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private BudgetActivity.Coin mCoin;

    public ItemBudgetRecyclerView(BudgetActivity.Coin mCargo, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
        this.mCoin = mCargo;
    }

    public void onProductCLick(){

    }

    @Bindable
    public BudgetActivity.Coin getCoin() {
        return mCoin;
    }
}
