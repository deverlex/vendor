package vn.needy.vendor.screen.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.datasource.model.Category;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.databinding.ActivityCategoriesBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.mainPage.MainPageFragment;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesActivity extends BaseActivity {

    public static final int RC_OK = 1101;
    public static final int RC_NOT_OK = 1102;

    public static final String CATEGORY = "_category";
    public static final String FROM_CLASS = "_from_class";

    private CategoriesContract.ViewModel mViewModel;

    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        String fromClass;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromClass = extras.getString(CategoriesActivity.FROM_CLASS);
        } else {
            fromClass = MainPageFragment.class.getSimpleName();
        }

        mNavigator = new Navigator(this);
        List<Category> categories = new ArrayList<>();
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        mViewModel = new CategoriesViewModel(this, mNavigator,
                categoryAdapter, SharedPrefsImpl.getInstance(), fromClass);

        CategoriesContract.Presenter presenter =
                new CategoriesPresenter(mViewModel, SharedPrefsImpl.getInstance());

        mViewModel.setPresenter(presenter);
        mViewModel.onStart();

        ActivityCategoriesBinding binding =
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

    @Override
    public void onBackPressed() {
        setResult(RC_NOT_OK);
        finish();
    }
}
