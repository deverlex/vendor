package vn.needy.vendor.screen.createProduct.childProduct;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.model.ProductPn;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductPresenter implements ChildProductContract.Presenter {

    private ChildProductContract.ViewModel mViewModel;

    public ChildProductPresenter(ChildProductContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getProducts() {
        // Get All Product of company
        List<ProductPn> productPns = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ProductPn productPn = new ProductPn();
            productPn.setId(i);
            productPns.add(productPn);
        }

        mViewModel.onUpdateProducts(productPns);
    }

    @Override
    public void getProductByCategory(String category) {
        List<ProductPn> productPns = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProductPn productPn = new ProductPn();
            productPn.setId(i);
            productPns.add(productPn);
        }

        mViewModel.onUpdateProducts(productPns);
    }
}
