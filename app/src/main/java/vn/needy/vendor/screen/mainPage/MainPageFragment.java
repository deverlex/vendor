package vn.needy.vendor.screen.mainPage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.databinding.FragmentMainPageBinding;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 21/10/2017.
 */

public class MainPageFragment extends Fragment {

    public static final String CLASS = MainPageFragment.class.getName();

    public static MainPageFragment getInstance() {
        return new MainPageFragment();
    }

    public static final int REQUEST_CODE = 1001;
    public static final String PRODUCT_TYPE = "_product_type";

    private MainPageConstract.ViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle extras = getArguments();
        Category category = extras.getParcelable(CategoriesActivity.CATEGORY);

        Navigator navigator = new Navigator(this);
        mViewModel = new MainPageViewModel(getContext(), navigator, category);

        FragmentMainPageBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main_page, container, false);
        binding.setViewModel((MainPageViewModel) mViewModel);

        return binding.getRoot();
    }

}
