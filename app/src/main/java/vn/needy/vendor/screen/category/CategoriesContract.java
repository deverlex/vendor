package vn.needy.vendor.screen.category;

import java.util.List;

import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 03/11/2017.
 */

public interface CategoriesContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onBackClicked();

        void onUpdateListCategory(List<CategoryWrapper> categories);

        void onUpdateListCategoryError(BaseException exception);

        void backActivity();
    }

    interface Presenter extends BasePresenter {

        void getCompanyCategoryPriceNow();

        void getCompanyCategoryPriceLater();

        void getCategoryPriceNow();

        void getCategoryPriceLater();

        void getCompanyCategories(String category);

        void getCategories(String category);
    }
}
