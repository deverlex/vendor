package vn.needy.vendor.screen.createProduct.childProduct;

import java.util.List;

import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.port.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 08/12/2017.
 */

interface ChildProductContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onBackClicked();

        void onDoneClicked();

        void onUpdateProducts(List<ProductPnWrapper> productPns);

        void onChooseCategory();

        void updateCategory(CategoryWrapper category);
    }

    interface Presenter extends BasePresenter {
        void getProducts();

        void getProductByCategory(String category);
    }
}
