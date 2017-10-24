package vn.needy.vendor.screen.personal;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentPesonalBinding;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 21/10/2017.
 */

public class PersonalFragment extends Fragment {

    public static PersonalFragment getInstance() {
        return new PersonalFragment();
    }

    private PersonalConstract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Navigator navigator = new Navigator(this);
        mViewModel = new PersonalViewModel(navigator);

        FragmentPesonalBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pesonal, container, false);
        binding.setViewModel((PersonalViewModel) mViewModel);
//        binding.setMainPage(this);
        return binding.getRoot();
    }
}
