package vn.needy.vendor.screen.budget.historyRecharge;

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
import vn.needy.vendor.databinding.FragmentBudbgetHistoryRechargeBinding;
import vn.needy.vendor.domain.Budget;
import vn.needy.vendor.screen.budget.BudgetActivity;
import vn.needy.vendor.screen.budget.BudgetRecyclerViewAdapter;

/**
 * Created by lion on 02/01/2018.
 */

public class BudgetHistoryRechargeFragment extends Fragment {


    public static BudgetHistoryRechargeFragment getInstance()
    {
        return new BudgetHistoryRechargeFragment();
    }

    private BudgetHistoryRechargeContract.ViewModel mViewModel;
    private BudgetHistoryRechargeContract.Presenter mPresenter;
    private BudgetRecyclerViewAdapter mAdapter;
    private List<BudgetActivity.Coin> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mList = new ArrayList<>();

        mAdapter = new BudgetRecyclerViewAdapter(getActivity() , mList);
        mViewModel = new BudgetHistoryRechargeViewModel(getActivity() , mAdapter);

        mPresenter = new BudgetHistoryRechargePresenter(getActivity() , mViewModel);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();;


        FragmentBudbgetHistoryRechargeBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_budbget_history_recharge
                , container , false);
        binding.setViewModel((BudgetHistoryRechargeViewModel) mViewModel);

        return binding.getRoot();
    }
}
