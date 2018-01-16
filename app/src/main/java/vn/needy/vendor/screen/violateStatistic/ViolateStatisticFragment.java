package vn.needy.vendor.screen.violateStatistic;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentViolateStatisticBinding;

/**
 * Created by lion on 11/01/2018.
 */

public class ViolateStatisticFragment extends Fragment {

    public static ViolateStatisticFragment getInstance(){
        return new ViolateStatisticFragment();
    }

    private ViolateStatisticContract.ViewModel mViewModel;
    private ViolateStatisticContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = new ViolateStatisticViewModel(getActivity());
        mPresenter = new ViolateStatisticPresenter();

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        FragmentViolateStatisticBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext())
                , R.layout.fragment_violate_statistic                , container , false);

        binding.setViewModel((ViolateStatisticViewModel) mViewModel);
        return binding.getRoot();
    }
}
