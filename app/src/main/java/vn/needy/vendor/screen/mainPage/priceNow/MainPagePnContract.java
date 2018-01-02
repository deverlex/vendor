package vn.needy.vendor.screen.mainPage.priceNow;

import java.util.List;

import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.port.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 02/01/2018.
 */

public interface MainPagePnContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickCategories();

        void updateCategory(CategoryWrapper category);

        void onUpdateProducts(List<ProductPnWrapper> productPns);
    }

    interface Presenter extends BasePresenter {
        void getProductByCategory(String category);
    }
}
