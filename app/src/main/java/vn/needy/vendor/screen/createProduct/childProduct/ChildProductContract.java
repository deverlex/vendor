package vn.needy.vendor.screen.createProduct.childProduct;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 08/12/2017.
 */

interface ChildProductContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onBackClicked();

        void onDoneClicked();
    }

    interface Presenter extends BasePresenter {

    }
}
