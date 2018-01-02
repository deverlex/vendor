package vn.needy.vendor.screen.mainPage.priceNow;

import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 02/01/2018.
 */

public interface MainPagePnContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickCategories();

        void updateCategory(CategoryWrapper category);
    }

    interface Presenter extends BasePresenter {

    }
}
