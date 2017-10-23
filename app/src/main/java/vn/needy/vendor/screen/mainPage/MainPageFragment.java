package vn.needy.vendor.screen.mainPage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentMainPageBinding;

/**
 * Created by lion on 21/10/2017.
 */

public class MainPageFragment extends Fragment {

    public static MainPageFragment getInstance() {
        return new MainPageFragment();
    }

    private MainPageConstract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new MainPageViewModel();

        FragmentMainPageBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false);
        binding.setViewModel((MainPageViewModel) mViewModel);
//        binding.setMainPage(this);
        return binding.getRoot();
    }
}
