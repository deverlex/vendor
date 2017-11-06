package vn.needy.vendor.screen.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.databinding.ActivityCategoriesBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesActivity extends BaseActivity {

    private CategoriesContract.ViewModel mViewModel;

    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        mNavigator = new Navigator(this);

        List<Category> categories = new ArrayList<>();
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        mViewModel = new CategoriesViewModel(this, mNavigator, categoryAdapter);

        ActivityCategoriesBinding

          binding =
                DataBindingUtil.setContentView(this, R.layout.activity_categories);
        binding.setViewModel((CategoriesViewModel) mViewModel);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
