package vn.needy.vendor.screen.createProduct;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.realm.RealmList;
import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityCreateProductPlBinding;
import vn.needy.vendor.model.FeeTransport;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.model.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.ImageAdapter;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.createProduct.childProduct.ChildProductFragment;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 28/11/2017.
 */

public class CreateProductPlActivity extends BaseActivity implements ChildProductFragment.OnCallbackReceived{

    public static final int RC_CHOOSE_IMAGE = 2683;
    public static final int RC_CHOOSE_CATEGORY = 1783;

    private CreateProductPlContract.ViewModel mViewModel;
    private Navigator mNavigator;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);

        mNavigator = new Navigator(this);

        List<Image> images = new ArrayList<>();
        ImageAdapter imageAdapter = new ImageAdapter(this, images);

        List<ProductPnWrapper> productPns = new ArrayList<>();
        ChildProductPlAdapter childProductPlAdapter = new ChildProductPlAdapter(this, productPns);

        RealmList<FeeTransport> feeTransports = new RealmList<>();
        FeeTransportPnAdapter feeTransportAdapter = new FeeTransportPnAdapter(this, feeTransports);

        mViewModel = new CreateProductPlViewModel(this, mNavigator, imageAdapter, childProductPlAdapter, feeTransportAdapter);
        CreateProductPlContract.Presenter presenter = new CreateProductPlPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        ActivityCreateProductPlBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create_product_pl);
        binding.setViewModel((CreateProductPlViewModel) mViewModel);
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
            // check for getAsync category
        } else if (requestCode == RC_CHOOSE_CATEGORY) {
            if (resultCode == CategoriesActivity.RC_OK) {
                // getAsync category and call update category in view model
                CategoryWrapper category = data.getExtras().getParcelable(CategoriesActivity.CATEGORY);
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

    // Call from ViewModel
    @Override
    protected <T extends Fragment> T initFragment(int target, @NonNull T fragment) {
        return super.initFragment(target, fragment);
    }

    // Call from ViewModel
    @Override
    protected <T extends Fragment> T initFragment(int target, @NonNull T fragment, @Nullable Bundle extras) {
        return super.initFragment(target, fragment, extras);
    }

    @Override
    public void onUpdateListChildProduct(List<ProductPnWrapper> productPns) {
        mViewModel.onUpdateListChildProduct(productPns);
    }
}
