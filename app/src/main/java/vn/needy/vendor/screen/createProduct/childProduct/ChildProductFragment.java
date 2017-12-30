package vn.needy.vendor.screen.createProduct.childProduct;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.FragmentChildProductBinding;
import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.model.wrapper.ProductPnWrapper;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.screen.category.CategoriesActivity;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductFragment extends Fragment {
    public static final int RC_CHOOSE_CATEGORY = 1784;
    public static final String PRODUCT_LIST = "_products";

    public static ChildProductFragment newInstance() {
        ChildProductFragment fragment = new ChildProductFragment();
        return fragment;
    }

    private ChildProductContract.ViewModel mViewModel;
    private VendorApi mVendorApi;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mVendorApi = VendorServiceClient.getInstance();

        List<ProductPnWrapper> checkedProductPns = getArguments().getParcelableArrayList(PRODUCT_LIST);
        if (checkedProductPns == null) {
            checkedProductPns = new ArrayList<>();
        }

        List<ProductPnWrapper> productPns = new ArrayList<>();
        ChildProductAdapter childProductAdapter = new ChildProductAdapter(getActivity(), productPns, checkedProductPns);

        mViewModel = new ChildProductViewModel(getActivity(), this, childProductAdapter);
        ChildProductContract.Presenter presenter = new ChildProductPresenter(mViewModel, mVendorApi);
        mViewModel.setPresenter(presenter);

        mViewModel.onStart();

        FragmentChildProductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_child_product, container, false);
        binding.setViewModel((ChildProductViewModel) mViewModel);
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

    public interface OnCallbackReceived {
        void onUpdateListChildProduct(List<ProductPnWrapper> productPns);
    }
}
