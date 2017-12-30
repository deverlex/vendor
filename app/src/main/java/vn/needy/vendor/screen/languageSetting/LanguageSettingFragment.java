package vn.needy.vendor.screen.languageSetting;


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
import vn.needy.vendor.databinding.FragmentLanguageSettingBinding;
import vn.needy.vendor.model.Language;

/**
 * Created by lion on 25/12/2017.
 */

public class LanguageSettingFragment extends Fragment {

    public static LanguageSettingFragment getInstance()
    {
        return new LanguageSettingFragment();
    }

    private LanguageSettingContract.ViewModel mViewModel;
    private LanguageSettingContract.Presenter mPresenter;
    private LanguageSettingAdapter mLanguageSettingAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        List<Language> listLanguage = new ArrayList<>();

        mLanguageSettingAdapter = new LanguageSettingAdapter(getContext(), listLanguage);
        mViewModel = new LanguageSettingViewModel(getActivity() , mLanguageSettingAdapter);
        mPresenter = new LanguageSettingPresenter(mViewModel, getActivity());

        mViewModel.setPresenter(mPresenter);

        mViewModel.onStart();

        FragmentLanguageSettingBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_language_setting, container
                , false);
        binding.setViewModel((LanguageSettingViewModel) mViewModel);
//        binding.setMainPage(this);
        return binding.getRoot();
    }
}
