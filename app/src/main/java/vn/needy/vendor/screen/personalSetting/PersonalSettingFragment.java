package vn.needy.vendor.screen.personalSetting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentPersonalSettingBinding;

/**
 * Created by lion on 25/12/2017.
 */

public class PersonalSettingFragment extends Fragment{
    public static PersonalSettingFragment getInstance(){
        return new PersonalSettingFragment();
    }

    private PersonalSettingContract.Presenter mPresenter;
    private PersonalSettingContract.ViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPersonalSettingBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_personal_setting, container , false);
        mPresenter = new PersonalSettingPresenter(getActivity());
        mViewModel = new PersonalSettingViewModel(getActivity());
        mViewModel.onStart();
        binding.setViewModel((PersonalSettingViewModel) mViewModel);
        return binding.getRoot();
    }
}
