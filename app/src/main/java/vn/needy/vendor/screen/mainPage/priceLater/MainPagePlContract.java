package vn.needy.vendor.screen.mainPage.priceLater;

import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 02/01/2018.
 */

public interface MainPagePlContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickCategories();

        void updateCategory(CategoryWrapper category);
    }

    interface Presenter extends BasePresenter {

    }
}
