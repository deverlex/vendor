package vn.needy.vendor.screen.createProduct;

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

import io.realm.RealmList;
import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityCreateProductPnBinding;
import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.port.wrapper.AttributeWrapper;
import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.BaseActivity;
import vn.needy.vendor.screen.ImageAdapter;
import vn.needy.vendor.screen.createProduct.attribute.AttributeFragment;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 08/11/2017.
 */

public class CreateProductPnActivity extends BaseActivity
        implements AttributeFragment.OnCallbackReceived {

    private static final String TAG = CreateProductPnActivity.class.getName();

    private CreateProductPnContract.ViewModel mViewModel;
    public static final int RC_CHOOSE_IMAGE = 2682;
    public static final int RC_CHOOSE_CATEGORY = 1782;

    private Navigator mNavigator;
    private VendorApi mVendorApi;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        List<Image> images = new ArrayList<>();
        ImageAdapter imageAdapter = new ImageAdapter(this, images);

        mNavigator = new Navigator(this);
        mVendorApi = VendorServiceClient.getInstance();

        List<AttributeWrapper> attributeWrappers = new ArrayList<>();
        AttributeProductPnAdapter attributeResultPnAdapter = new AttributeProductPnAdapter(this, attributeWrappers);

        RealmList<FeeTransport> feeTransports = new RealmList<>();
        FeeTransportPnAdapter feeTransportAdapter = new FeeTransportPnAdapter(this, feeTransports);

        mViewModel = new CreateProductPnViewModel(this, mNavigator, imageAdapter, attributeResultPnAdapter, feeTransportAdapter);
        CreateProductPnContract.Presenter presenter = new CreateProductPnPresenter(this, mViewModel, mVendorApi);
        mViewModel.setPresenter(presenter);

        ActivityCreateProductPnBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_product_pn);
        binding.setViewModel((CreateProductPnViewModel) mViewModel);
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

    @Override
    public void onUpdateListAttribute(List<AttributeWrapper> attributes) {
        mViewModel.onSelectedListAttribute(attributes);
    }
}
