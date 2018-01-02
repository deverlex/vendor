package vn.needy.vendor.screen.mainPage.priceNow;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentMainPagePnBinding;
import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.port.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.mainPage.itemProduct.ProductPnAdapter;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePnFragment extends Fragment {
    public static final int RC_CHOOSE_CATEGORY = 1001;

    public static MainPagePnFragment newInstance() {
        MainPagePnFragment fragment = new MainPagePnFragment();
        return fragment;
    }

    private MainPagePnContract.ViewModel mViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Navigator navigator = new Navigator(this);

        List<ProductPnWrapper> productPns = new ArrayList<>();
        ProductPnAdapter productPnAdapter = new ProductPnAdapter(getActivity(), productPns);

        mViewModel = new MainPagePnViewModel(getActivity(), this,  navigator, productPnAdapter);
        MainPagePnContract.Presenter presenter = new MainPagePnPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        FragmentMainPagePnBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main_page_pn, container, false);
        mViewModel.onStart();
        binding.setViewModel((MainPagePnViewModel) mViewModel);
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
