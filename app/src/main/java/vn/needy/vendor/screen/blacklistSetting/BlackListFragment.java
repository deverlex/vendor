package vn.needy.vendor.screen.blacklistSetting;

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
import vn.needy.vendor.databinding.FragmentBlackListSettingBinding;
import vn.needy.vendor.model.BlockUser;

/**
 * Created by lion on 25/12/2017.
 */

public class BlackListFragment extends Fragment{
    public static BlackListFragment getInstance()
    {
        return new BlackListFragment();
    }

    private BlacklistSettingContract.Presenter mPresenter;
    private BlackListSettingAdapter mAdapter;
    private BlacklistSettingContract.ViewModel mViewModel;
    private List<BlockUser> mListUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBlackListSettingBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_black_list_setting , container , false);
        mListUser = new ArrayList<>();

        mAdapter = new BlackListSettingAdapter(getActivity() , mListUser);
        mViewModel = new BlacklistSettingViewModel(getActivity(), mAdapter);
        mPresenter = new BlackListPresenter(mViewModel , getActivity());
        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        binding.setViewModel((BlacklistSettingViewModel) mViewModel);
        return binding.getRoot();
    }
}
