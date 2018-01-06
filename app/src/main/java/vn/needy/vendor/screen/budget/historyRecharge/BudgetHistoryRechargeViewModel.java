package vn.needy.vendor.screen.budget.historyRecharge;

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

public class BudgetHistoryRechargeViewModel extends BaseObservable implements BudgetHistoryRechargeContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{

    private Context mContext;
    private boolean mVisibilityRecyclerView;
    private  BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;
    private BudgetRecyclerViewAdapter mAdapter;
    private BudgetHistoryRechargeContract.Presenter mPresenter;

    public BudgetHistoryRechargeViewModel(Context mContext, BudgetRecyclerViewAdapter adapter) {
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
    public void setPresenter(BudgetHistoryRechargeContract.Presenter presenter) {
        mPresenter = presenter;
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
    public BudgetRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    @Bindable
    public boolean isVisibilityRecyclerView() {
        return mVisibilityRecyclerView;
    }
}
