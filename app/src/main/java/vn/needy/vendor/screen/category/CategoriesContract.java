package vn.needy.vendor.screen.category;

import java.util.List;

import vn.needy.vendor.model.Category;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 03/11/2017.
 */

public interface CategoriesContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onBackClicked();

        void onUpdateListCategory(List<Category> categories);

        void onUpdateListCategoryError(BaseException exception);

        void backActivity();
    }

    interface Presenter extends BasePresenter {

        void getCompanyLinkCategoryPriceNow();

        void getCompanyLinkCategoryPriceLater();

        void getLinkCategoryPriceNow();

        void getLinkCategoryPriceLater();

        void getCompanyLinkCategories(String category);

        void getLinkCategories(String category);
    }
}
