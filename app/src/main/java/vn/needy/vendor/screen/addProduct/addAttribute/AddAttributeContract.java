package vn.needy.vendor.screen.addProduct.addAttribute;

import java.util.List;

import vn.needy.vendor.database.model.Attribute;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 04/12/2017.
 */

interface AddAttributeContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onBackClicked();

        void onDoneClicked();

        void onListAttributeLoaded(List<Attribute> attributes);
    }

    interface Presenter extends BasePresenter {
        void onGetListAttributes(Category category);

        boolean onValidateDataInput(List<Object> attrs);
    }
}
