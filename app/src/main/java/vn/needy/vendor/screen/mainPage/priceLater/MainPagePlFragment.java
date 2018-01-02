package vn.needy.vendor.screen.mainPage.priceLater;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentMainPagePlBinding;
import vn.needy.vendor.databinding.FragmentMainPagePnBinding;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.mainPage.priceNow.MainPagePnContract;
import vn.needy.vendor.screen.mainPage.priceNow.MainPagePnViewModel;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePlFragment extends Fragment {
    public static final int RC_CHOOSE_CATEGORY = 1001;

    public static MainPagePlFragment newInstance() {
        MainPagePlFragment fragment = new MainPagePlFragment();
        return fragment;
    }

    private MainPagePlContract.ViewModel mViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Navigator navigator = new Navigator(this);
        mViewModel = new MainPagePlViewModel(getActivity(), this,  navigator);
        FragmentMainPagePlBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main_page_pl, container, false);
        binding.setViewModel((MainPagePlViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CHOOSE_CATEGORY) {
            if (resultCode == CategoriesActivity.RC_OK) {
                // getAsync category and call update category in view model
                CategoryWrapper category = data.getExtras().getParcelable(CategoriesActivity.CATEGORY);
                mViewModel.updateCategory(category);
            }
        }
    }
}
