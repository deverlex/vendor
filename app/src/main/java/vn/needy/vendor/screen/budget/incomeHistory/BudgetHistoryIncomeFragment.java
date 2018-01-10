package vn.needy.vendor.screen.budget.incomeHistory;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentBudbgetHistoryIncomeBinding;
import vn.needy.vendor.screen.budget.BudgetActivity;
import vn.needy.vendor.screen.budget.BudgetRecyclerViewAdapter;

/**
 * Created by lion on 02/01/2018.
 */

public class BudgetHistoryIncomeFragment extends Fragment {

    public static BudgetHistoryIncomeFragment getInstance()
    {
        return new BudgetHistoryIncomeFragment();
    }

    private BudgetHistoryIncomeContract.Presenter mPresenter;
    private BudgetHistoryIncomeContract.ViewModel mViewModel;
    private BudgetRecyclerViewAdapter mAdapter;
    private List<BudgetActivity.Coin> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mList = new ArrayList<>();
        mAdapter = new BudgetRecyclerViewAdapter(getActivity() , mList);

        mViewModel = new BudgetHistoryIncomeViewModel(getActivity() , mAdapter);
        mPresenter = new BudgetHistoryIncomePresenter(getActivity() , mViewModel);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        FragmentBudbgetHistoryIncomeBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_budbget_history_income
                , container , false);
        binding.setViewModel((BudgetHistoryIncomeViewModel) mViewModel);

        return binding.getRoot();
    }
}
