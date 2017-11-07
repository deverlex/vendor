package vn.needy.vendor.screen.category;

import java.util.List;

import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 03/11/2017.
 */

public interface CategoriesContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListCategorySuccess(List<Category> categories);

        void onGetListCategoryError(BaseException exception);

        void onGetProductOfCompany();
    }

    interface Presenter extends BasePresenter {

        void getListCategory();

        void getListSubCategories(String category);
    }
}
