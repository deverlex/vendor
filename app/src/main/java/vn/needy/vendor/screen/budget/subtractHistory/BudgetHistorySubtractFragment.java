package vn.needy.vendor.screen.budget.subtractHistory;

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
import vn.needy.vendor.databinding.FragmentBudbgetHistorySubtractBinding;
import vn.needy.vendor.screen.budget.BudgetActivity;
import vn.needy.vendor.screen.budget.BudgetRecyclerViewAdapter;

/**
 * Created by lion on 02/01/2018.
 */

public class BudgetHistorySubtractFragment extends Fragment {


    public static BudgetHistorySubtractFragment getInstance()
    {
        return new BudgetHistorySubtractFragment();
    }

    private BudgetHistorySubtractContract.ViewModel mViewModel;
    private BudgetHistorySubtractContract.Presenter mPresenter;
    private BudgetRecyclerViewAdapter mAdapter;
    private List<BudgetActivity.Coin> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mList = new ArrayList<>();
        mAdapter = new BudgetRecyclerViewAdapter(getActivity() , mList);
        mViewModel = new BudgetHistorySubtractViewModel(getActivity() , mAdapter);

        mPresenter = new BudgetHistorySubtractPresenter(getActivity() , mViewModel);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        FragmentBudbgetHistorySubtractBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_budbget_history_subtract
                , container , false);

        binding.setViewModel((BudgetHistorySubtractViewModel) mViewModel);

        return binding.getRoot();
    }
}
