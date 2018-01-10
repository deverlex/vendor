package vn.needy.vendor.screen.budget;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.domain.Budget;

/**
 * Created by truongpq on 04/12/2017.
 */

public class BudgetViewModel extends BaseObservable implements BudgetContract.ViewModel {

    private Budget mBudget;
    private Context mContext;
    private BudgetContract.Presenter mPresenter;
    private BudgetSectionsAdapter mAdapter;


    public BudgetViewModel(Context mContext , BudgetSectionsAdapter adapter) {
        this.mContext = mContext;
        mAdapter = adapter;

        mBudget = new Budget();

    }

    @Override
    public void onStart() {
        mBudget.setAmount(0);
        mBudget.setHold(2000000);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(BudgetContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Bindable
    public String getAmount() {
        return mBudget.getAmount() + "";
    }

    @Bindable
    public String getHold() {
        return mBudget.getHold() + "";
    }

    @Bindable
    public BudgetSectionsAdapter getAdapter() {
        return mAdapter;
    }
}
