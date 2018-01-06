package vn.needy.vendor.screen.budget.historyIncome;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

import vn.needy.vendor.domain.Budget;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.budget.BudgetActivity;
import vn.needy.vendor.screen.budget.BudgetRecyclerViewAdapter;

/**
 * Created by minh_dai on 02/01/2018.
 */

public class BudgetHistoryIncomeViewModel extends BaseObservable implements BudgetHistoryIncomeContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{

    private Context mContext;
    private BudgetRecyclerViewAdapter mAdapter;
    private boolean mVisibilityRecyclerView;
    private  BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;
    private BudgetHistoryIncomeContract.Presenter mPresenter;

    public BudgetHistoryIncomeViewModel(Context mContext, BudgetRecyclerViewAdapter adapter) {
        this.mContext = mContext;
        mAdapter = adapter;
        mVisibilityRecyclerView = false;
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.getListProduct();

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(BudgetHistoryIncomeContract.Presenter presenter) {
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
    public void getCoinList(List<BudgetActivity.Coin> coins) {
        mAdapter.updateData(coins);
        mVisibilityRecyclerView = coins.size() == 0 ? false : true;
    }

    @Override
    public void onPersonalEarnsCoinClick() {

    }

    @Bindable
    public boolean isVisibilityRecyclerView() {
        return mVisibilityRecyclerView;
    }
}
