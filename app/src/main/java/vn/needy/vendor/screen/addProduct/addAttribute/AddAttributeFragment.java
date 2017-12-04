package vn.needy.vendor.screen.addProduct.addAttribute;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.databinding.FragmentMainPageBinding;
import vn.needy.vendor.screen.mainPage.MainPageViewModel;

/**
 * Created by lion on 04/12/2017.
 */

public class AddAttributeFragment extends Fragment {

    public static final AddAttributeFragment getInstance() {
        return new AddAttributeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle extras = getArguments();
//        Category category = extras.getParcelable();

        // data binding
        FragmentMainPageBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main_page, container, false);
//        binding.setViewModel((MainPageViewModel) mViewModel);
        return binding.getRoot();
    }
}
