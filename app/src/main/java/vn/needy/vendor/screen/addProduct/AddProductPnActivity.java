package vn.needy.vendor.screen.addProduct;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.model.Attribute;
import vn.needy.vendor.model.Category;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.databinding.ActivityAddProductPnBinding;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.ImageAdapter;
import vn.needy.vendor.screen.addProduct.addAttribute.AddAttributeFragment;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 08/11/2017.
 */

public class AddProductPnActivity extends BaseActivity
        implements AddAttributeFragment.OnCallbackReceived {

    private static final String TAG = AddProductPnActivity.class.getName();

    private AddProductPnContract.ViewModel mViewModel;
    public static final int RC_CHOOSE_IMAGE = 2682;
    public static final int RC_CHOOSE_CATEGORY = 1782;

    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        List<Image> images = new ArrayList<>();
        ImageAdapter imageAdapter = new ImageAdapter(this, images);

        mNavigator = new Navigator(this);

        mViewModel = new AddProductPnViewModel(this, mNavigator, imageAdapter);
        AddProductPnContract.Presenter presenter = new AddProductPnPresenter(this, mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityAddProductPnBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_add_product_pn);
        binding.setViewModel((AddProductPnViewModel) mViewModel);
    }

    @Override
    public Fragment initFragment(@IdRes int target,
                                 @NonNull Fragment fragment, @Nullable Bundle extras) {
        return super.initFragment(target, fragment, extras);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            List<Image> images = new LinkedList<>();
            for (Uri uri : Matisse.obtainResult(data)) {
                images.add(new Image(this, uri.toString()));
            }
            // Update images view
            mViewModel.onSelectedListImages(images);
            // check for get category
        } else if (requestCode == RC_CHOOSE_CATEGORY) {
            if (resultCode == CategoriesActivity.RC_OK) {
                // get category and call update category in view model
                Category category = data.getExtras().getParcelable(CategoriesActivity.CATEGORY);
                mViewModel.updateCategory(category);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onUpdateListAttribute(List<Attribute> attributes) {
        mViewModel.onSelectedListAttribute(attributes);
    }
}
