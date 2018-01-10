package vn.needy.vendor.screen.budget.subtractHistory;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.budget.BudgetActivity;
import vn.needy.vendor.screen.budget.BudgetRecyclerViewAdapter;

/**
 * Created by minh_dai on 02/01/2018.
 */

public class BudgetHistorySubtractViewModel extends BaseObservable implements BudgetHistorySubtractContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{

    private Context mContext;
    private BudgetRecyclerViewAdapter mAdapter;
    private boolean mVisibilityRecyclerView;
    private  BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;
    private BudgetHistorySubtractContract.Presenter mPresenter;

    public BudgetHistorySubtractViewModel(Context mContext, BudgetRecyclerViewAdapter adapter) {
        this.mContext = mContext;
        mAdapter = adapter;
        mVisibilityRecyclerView = false;
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.setListCoin();

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(BudgetHistorySubtractContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public BudgetRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {

    }

    @Override
    public void getListCoin(List<BudgetActivity.Coin> coins) {
        mAdapter.updateData(coins);
        mVisibilityRecyclerView = coins.size() == 0 ? false : true;
    }

    @Override
    public void onClickPersonalEarnsCoins() {

    }

    @Bindable
    public boolean isVisibilityRecyclerView() {
        return mVisibilityRecyclerView;
    }
}
